package app.ui.controller;

import app.AppState;
import app.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyReservationsController {
    @FXML private Label colLocation;
    @FXML private Label colStatus;

    private final DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    private void initialize() {
        // لو ما فيه حجز محفوظ، اعمل واحد افتراضي لـ City Mall للعرض
        if (AppState.lastReservation == null) {
            LocalDateTime now = LocalDateTime.now();
            AppState.lastReservation = new AppState.Reservation("City Mall", now, 2);
        }

        var res = AppState.lastReservation;
        var lot = AppState.LOTS.getOrDefault(res.lotKey, new AppState.Lot(res.lotKey, "", 0, null));

        colLocation.setText(lot.name + (lot.location.isEmpty() ? "" : " — " + lot.location));

        LocalDateTime now = LocalDateTime.now();
        String status;
        if (now.isBefore(res.start)) {
            status = "Upcoming — starts at " + timeFmt.format(res.start);
        } else if (now.isBefore(res.end())) {
            status = "Active — until " + timeFmt.format(res.end());
        } else {
            status = "Completed — ended at " + timeFmt.format(res.end());
        }
        colStatus.setText(status);
    }

    @FXML private void goHome() { Router.go("home.fxml", "Smart Parking | Home"); }
}
