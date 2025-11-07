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
        var rows = new app.dao.ReservationDAO().listAllHumanReadable();
        if (rows.isEmpty()) {
            colLocation.setText("");
            colStatus.setText("No active reservations");
            return;
        }

        // آخر حجز
        var r = rows.get(0);
        String lotKey = r[0];
        long start = Long.parseLong(r[1]);
        int hrs = Integer.parseInt(r[2]);

        var startDT = java.time.Instant.ofEpochSecond(start)
                .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
        var endDT = startDT.plusHours(hrs);
        var now = java.time.LocalDateTime.now();

        colLocation.setText(lotKey);

        String status = now.isBefore(startDT)
                ? "Upcoming — starts at " + java.time.format.DateTimeFormatter.ofPattern("HH:mm").format(startDT)
                : (now.isBefore(endDT)
                ? "Active — until " + java.time.format.DateTimeFormatter.ofPattern("HH:mm").format(endDT)
                : "Completed — ended at " + java.time.format.DateTimeFormatter.ofPattern("HH:mm").format(endDT));

        colStatus.setText(status);
    }


    @FXML
    private void goHome() {
        Router.go("home.fxml", "Smart Parking | Home");
    }
}
