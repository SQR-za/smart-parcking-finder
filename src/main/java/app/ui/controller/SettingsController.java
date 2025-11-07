package app.ui.controller;

import app.Router;
import javafx.fxml.FXML;

public class SettingsController {
    @FXML private void logout(){ Router.go("login.fxml", "Smart Parking | Login"); }
    @FXML private void goHome(){ Router.go("home.fxml", "Smart Parking | Home"); }
}
