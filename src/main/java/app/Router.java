package app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;

public class Router {
    private static Stage stage;

    public static void init(Stage s) { stage = s; }

    public static void go(String fxml, String title) {
        try {
            // لازم يبدأ بـ "/" ويطابق resources/app/ui/...
            String path = "/app/ui/" + fxml;
            URL url = Router.class.getResource(path);
            if (url == null) {
                throw new IllegalStateException("FXML not found at: " + path);
            }
            var loader = new FXMLLoader(url);
            stage.setTitle(title);
            stage.setScene(new Scene(loader.load(), 900, 600));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).showAndWait();
        }
    }
}
