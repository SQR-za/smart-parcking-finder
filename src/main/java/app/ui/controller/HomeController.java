package app.ui.controller;

import app.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import app.AppState;

public class HomeController {

    // Avatar
    @FXML private ImageView imgAvatar;

    // ImageViews للكروت
    @FXML private ImageView imgCityMall, imgDowntownPlaza, imgTechPark, imgMetroStation;

    // Labels للأسعار
    @FXML private Label priceCityMall, priceDowntownPlaza, priceTechPark, priceMetroStation;

    // Buttons (اختياري لو تبغى تتعامل معها مباشرة)
    @FXML private Button btnCityMall, btnDowntownPlaza, btnTechPark, btnMetroStation;


    @FXML
    private void initialize() {
        // تحميل الصور من resources (لو تغيّر الاسم عدّله هنا)
        setImageSafe(imgAvatar, "/app/ui/img/avatar.png");
        setImageSafe(imgCityMall, "/app/ui/img/city_mall.png");
        setImageSafe(imgDowntownPlaza, "/app/ui/img/downtown_plaza.png");
        setImageSafe(imgTechPark, "/app/ui/img/tech_park.png");
        setImageSafe(imgMetroStation, "/app/ui/img/metro_station.png");

        // ضبط الأسعار (نكتب بالدولار هنا عشان ما نهرب $ في FXML)
        priceCityMall.setText("$5/hour");
        priceDowntownPlaza.setText("$3/hour");
        priceTechPark.setText("$4/hour");
        priceMetroStation.setText("$6/hour");
    }

    private void setImageSafe(ImageView view, String classpathUrl) {
        try {
            var url = getClass().getResource(classpathUrl);
            if (url != null) {
                view.setImage(new Image(url.toExternalForm()));
            }
        } catch (Exception ignored) {}
    }

    // أحداث الأزرار
    @FXML private void reserveCityMall()       { goReserve(); }
    @FXML private void reserveDowntownPlaza()  { goReserve(); }
    @FXML private void reserveTechPark()       { goReserve(); }
    @FXML private void reserveMetroStation()   { goReserve(); }

    private void goReserve() {
        Router.go("reserve.fxml", "Smart Parking | Reserve");
    }

    // تنقل شريط علوي (لو احتجته لاحقاً)
    @FXML private void goHome()           { Router.go("home.fxml", "Smart Parking | Home"); }
    @FXML private void goMyReservations() { Router.go("my_reservations.fxml", "Smart Parking | My Reservations"); }
    @FXML private void goSettings()       { Router.go("settings.fxml", "Smart Parking | Settings"); }
}
