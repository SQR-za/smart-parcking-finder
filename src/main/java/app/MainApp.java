package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override public void start(Stage stage) {
        app.data.Migrations.run();
        Router.init(stage);
        Router.go("login.fxml", "Smart Parking | Login");
    }
    public static void main(String[] args){ launch(args); }
}
