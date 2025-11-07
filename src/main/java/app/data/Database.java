package app.data;

import java.nio.file.*;
import java.sql.*;

public class Database {
    private static Connection CONN;

    public static synchronized Connection get() throws SQLException {
        if (CONN == null || CONN.isClosed()) {
            // يخزن قاعدة البيانات في مجلد المستخدم
            Path home = Path.of(System.getProperty("user.home"), ".smartparking");
            try { Files.createDirectories(home); } catch (Exception ignored) {}
            String url = "jdbc:sqlite:" + home.resolve("app.db").toAbsolutePath();
            CONN = DriverManager.getConnection(url);
            try (var st = CONN.createStatement()) { st.execute("PRAGMA foreign_keys = ON"); }
        }
        return CONN;
    }
}
