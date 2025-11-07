package app.dao;

import app.data.Database;
import java.sql.*;
import java.util.*;

public class ReservationDAO {
    public void insert(String lotKey, long startEpochSec, int hours) {
        try (var conn = Database.get();
             var ps = conn.prepareStatement(
                     "INSERT INTO reservations(lot_key,start_ts,duration_hours) VALUES(?,?,?)")) {
            ps.setString(1, lotKey);
            ps.setLong(2, startEpochSec);
            ps.setInt(3, hours);
            ps.executeUpdate();
        } catch (Exception e) {
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR,
                    "Save reservation failed: " + e.getMessage()).showAndWait();
        }
    }

    public List<String[]> listAllHumanReadable() {
        var out = new ArrayList<String[]>();
        try (var conn = Database.get();
             var ps = conn.prepareStatement(
                     "SELECT lot_key, start_ts, duration_hours FROM reservations ORDER BY id DESC")) {
            var rs = ps.executeQuery();
            while (rs.next()) {
                String lot = rs.getString(1);
                long start = rs.getLong(2);
                int hrs = rs.getInt(3);
                out.add(new String[]{ lot, String.valueOf(start), String.valueOf(hrs) });
            }
        } catch (Exception e) {
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR,
                    "Load reservations failed: " + e.getMessage()).showAndWait();
        }
        return out;
    }
}
