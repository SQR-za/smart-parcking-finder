package app.ui.controller;

import app.AppState;
import app.Router;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.stream.IntStream;

public class ReserveController {
    @FXML private ImageView lotImage;
    @FXML private Label lotName, lotLocation, lotRate, priceLabel;
    @FXML private ChoiceBox<Integer> durationChoice;
    @FXML private ChoiceBox<String> startTimeChoice;

    private AppState.Lot current;

    @FXML
    private void initialize() {
        // حدّد الموقف المختار (أو أول واحد كافتراضي)
        String key = (AppState.selectedLotKey != null) ? AppState.selectedLotKey
                : AppState.LOTS.keySet().iterator().next();
        current = AppState.LOTS.get(key);

        // عرّض البيانات
        lotName.setText(current.name);
        lotLocation.setText(current.location);
        lotRate.setText(String.format("$%.0f/hour", current.hourly));

        // الصورة
        var url = getClass().getResource(current.imagePath);
        if (url != null) lotImage.setImage(new Image(url.toExternalForm()));

        // المدة (1..24 ساعة)
        IntStream.rangeClosed(1, 24).forEach(durationChoice.getItems()::add);
        durationChoice.getSelectionModel().select(Integer.valueOf(1));

        // أوقات بداية (كل 30 دقيقة من 08:00 إلى 22:30)
        for (int h = 8; h <= 22; h++) {
            startTimeChoice.getItems().add(String.format("%02d:00", h));
            startTimeChoice.getItems().add(String.format("%02d:30", h));
        }
        startTimeChoice.getSelectionModel().select("10:00");

        // تحديث السعر عند تغيير المدة
        durationChoice.getSelectionModel().selectedItemProperty().addListener((o, oldV, newV) -> updatePrice());
        updatePrice();
    }

    private void updatePrice() {
        Integer hrs = durationChoice.getValue();
        if (hrs == null) { priceLabel.setText("Total: $0.00"); return; }
        double total = current.hourly * hrs;
        priceLabel.setText(String.format("Total: $%.2f", total));
    }

    @FXML
    private void confirm() {
        // خزّن الحجز للعرض
        var startStr = startTimeChoice.getValue(); // مثال "10:00"
        int hour = Integer.parseInt(startStr.substring(0, 2));
        int minute = Integer.parseInt(startStr.substring(3, 5));
        var start = java.time.LocalDate.now().atTime(hour, minute);

        AppState.lastReservation = new AppState.Reservation(
                AppState.selectedLotKey != null ? AppState.selectedLotKey : "City Mall",
                start,
                durationChoice.getValue() != null ? durationChoice.getValue() : 1
        );

        Router.go("my_reservations.fxml", "Smart Parking | My Reservations");
    }

    @FXML private void goHome() {
        Router.go("home.fxml", "Smart Parking | Home");
    }
}
