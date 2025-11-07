package app.ui.controller;

import app.Router;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML private void onLogin() {
        // DEV: تخطي تسجيل الدخول مؤقتًا
        Router.go("home.fxml", "Smart Parking | Home");
    }
}
