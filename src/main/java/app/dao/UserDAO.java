package app.dao;

import app.data.Database;
import java.sql.*;

public class UserDAO {
    public boolean checkPlain(String username, String password) {
        try (var conn = Database.get();
             var ps = conn.prepareStatement(
                     "SELECT 1 FROM users WHERE username=? AND password=?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            var rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR,
                    "Login failed: " + e.getMessage()).showAndWait();
            return false;
        }
    }
}
