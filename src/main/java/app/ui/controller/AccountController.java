package app.ui.controller;

import app.AppState;
import app.Router;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountController {

    @FXML private TableView<ReservationRow> reservationsTable;
    @FXML private TableColumn<ReservationRow, String> locationColumn;
    @FXML private TableColumn<ReservationRow, String> statusColumn;
    @FXML private Label noReservationsLabel;

    private final DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    private void initialize() {
        // ربط الأعمدة بالخصائص
        locationColumn.setCellValueFactory(data -> data.getValue().location);
        statusColumn.setCellValueFactory(data -> data.getValue().status);

        // لو ما فيه حجز محفوظ، أنشئ واحد افتراضي للعرض
        if (AppState.lastReservation == null) {
            LocalDateTime now = LocalDateTime.now();
            AppState.lastReservation = new AppState.Reservation("City Mall", now, 2);
        }

        var res = AppState.lastReservation;
        var lot = AppState.LOTS.getOrDefault(res.lotKey,
                new AppState.Lot(res.lotKey, "", 0, null));

        String locationText = lot.name + (lot.location.isEmpty() ? "" : " — " + lot.location);

        // احسب الحالة
        LocalDateTime now = LocalDateTime.now();
        String statusText;
        if (now.isBefore(res.start)) {
            statusText = "Upcoming — starts at " + timeFmt.format(res.start);
        } else if (now.isBefore(res.end())) {
            statusText = "Active — until " + timeFmt.format(res.end());
        } else {
            statusText = "Completed — ended at " + timeFmt.format(res.end());
        }

        // عبّي الجدول أو أظهر الرسالة
        reservationsTable.setItems(FXCollections.observableArrayList(
                new ReservationRow(locationText, statusText)
        ));
        boolean hasRows = !reservationsTable.getItems().isEmpty();
        noReservationsLabel.setVisible(!hasRows);
    }

    @FXML
    private void goBack() {
        Router.go("home.fxml", "Smart Parking | Home");
    }

    // صف الجدول (موديل بسيط)
    public static class ReservationRow {
        final SimpleStringProperty location;
        final SimpleStringProperty status;
        ReservationRow(String location, String status) {
            this.location = new SimpleStringProperty(location);
            this.status = new SimpleStringProperty(status);
        }
    }
}
