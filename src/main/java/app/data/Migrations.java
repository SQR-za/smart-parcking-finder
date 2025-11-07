package app.data;

import java.sql.*;

public class Migrations {
    public static void run() {
        try (var conn = Database.get(); var st = conn.createStatement()) {
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS users(
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  username TEXT UNIQUE NOT NULL,
                  password TEXT NOT NULL
                );
            """);

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS reservations(
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  lot_key TEXT NOT NULL,
                  start_ts INTEGER NOT NULL,
                  duration_hours INTEGER NOT NULL
                );
            """);

            // حساب اختبار بسيط: test / 1234
            try (var ps = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username=?")) {
                ps.setString(1, "test");
                var rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    try (var ins = conn.prepareStatement(
                            "INSERT INTO users(username,password) VALUES(?,?)")) {
                        ins.setString(1, "test");
                        ins.setString(2, "1234"); // بدون تشفير كما طلبت
                        ins.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            // اكسبشن هاندلنق بسيط للعرض
            e.printStackTrace();
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR,
                    "DB init failed: " + e.getMessage()).showAndWait();
        }
    }
}
