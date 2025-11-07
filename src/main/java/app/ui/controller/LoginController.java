package app.ui.controller;

import app.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void onLogin() {
        String u = usernameField.getText();
        String p = passwordField.getText();
        if (u == null || u.isBlank() || p == null || p.isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Please enter username & password").showAndWait();
            return;
        }
        boolean ok = new app.dao.UserDAO().checkPlain(u, p);
        if (ok) {
            app.Router.go("home.fxml", "Smart Parking | Home");
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username/password").showAndWait();
        }
    }

}
