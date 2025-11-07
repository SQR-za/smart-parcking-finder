package app.ui.controller;

import app.AppState;
import app.Router;
import javafx.fxml.FXML;

/**
 * Home screen controller.
 * يعتمد على أزرار في home.fxml مثل:
 * - onAction="#reserveCityMall"
 * - onAction="#reserveDowntownPlaza"
 * - onAction="#reserveTechPark"
 * - onAction="#reserveMetroStation"
 * - onAction="#openReservationsEmpty"
 * - (اختياري) onAction="#goHome" / "#goMyReservations" / "#goSettings" / "#goReserve"
 */
public class HomeController {

    // ====== أزرار Reserve لكل بطاقة ======

    @FXML
    private void reserveCityMall() {
        AppState.selectedLotKey = "City Mall";
        Router.go("reserve.fxml", "Smart Parking | Reserve");
    }

    @FXML
    private void reserveDowntownPlaza() {
        AppState.selectedLotKey = "Downtown Plaza";
        Router.go("reserve.fxml", "Smart Parking | Reserve");
    }

    @FXML
    private void reserveTechPark() {
        AppState.selectedLotKey = "Tech Park";
        Router.go("reserve.fxml", "Smart Parking | Reserve");
    }

    @FXML
    private void reserveMetroStation() {
        AppState.selectedLotKey = "Metro Station";
        Router.go("reserve.fxml", "Smart Parking | Reserve");
    }

    /**
     * دالة عامة في حال عندك زر "Reserve" بدون تحديد بطاقة معينة في الـFXML.
     * إذا كانت AppState.selectedLotKey معيّنة مسبقًا من الصفحة الرئيسية سنستخدمها،
     * وإلا نترك ReserveController يختار الافتراضي.
     */
    @FXML
    private void goReserve() {
        Router.go("reserve.fxml", "Smart Parking | Reserve");
    }

    // ====== زر My Reservations (يعرض الصفحة فاضية) ======

    /**
     * يفتح صفحة الحجوزات فارغة بشكل صريح:
     * - يمسح أي حجز سابق
     * - يرفع فلاغ forceEmptyReservations ليضمن أن الصفحة تعرض "No active reservations"
     */
    @FXML
    private void openReservationsEmpty() {
        AppState.lastReservation = null;            // امسح أي حجز محفوظ
        AppState.forceEmptyReservations = true;     // اطلب عرضًا فارغًا لمرة واحدة
        Router.go("my_reservations.fxml", "Smart Parking | My Reservations");
    }

    // ====== تنقّلات عامة (اختيارية حسب استخدامك في التولبار) ======

    @FXML
    private void goHome() {
        Router.go("home.fxml", "Smart Parking | Home");
    }

    @FXML
    private void goMyReservations() {
        // يفتح صفحة الحجوزات بالحالة الحالية (بدون إجبارها على الفِراغ)
        Router.go("my_reservations.fxml", "Smart Parking | My Reservations");
    }

    @FXML
    private void goSettings() {
        Router.go("settings.fxml", "Smart Parking | Settings");
    }
}
