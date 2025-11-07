package app;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class AppState {
    public static String selectedLotKey = null;

    public static class Lot {
        public final String name, location, imagePath;
        public final double hourly;
        public Lot(String name, String location, double hourly, String imagePath) {
            this.name = name; this.location = location; this.hourly = hourly; this.imagePath = imagePath;
        }
    }

    // بيانات ستيج 2
    public static final Map<String, Lot> LOTS = new LinkedHashMap<>() {{
        put("City Mall",      new Lot("City Mall",      "Downtown",       5.0, "/app/ui/img/city_mall.png"));
        put("Downtown Plaza", new Lot("Downtown Plaza", "City Center",    3.0, "/app/ui/img/downtown_plaza.png"));
        put("Tech Park",      new Lot("Tech Park",      "Business Dist.", 4.0, "/app/ui/img/tech_park.png"));
        put("Metro Station",  new Lot("Metro Station",  "Central",        6.0, "/app/ui/img/metro_station.png"));
    }};

    // حجز واحد للعرض (showcase)
    public static class Reservation {
        public final String lotKey;
        public final LocalDateTime start;
        public final int durationHours;
        public Reservation(String lotKey, LocalDateTime start, int durationHours) {
            this.lotKey = lotKey; this.start = start; this.durationHours = durationHours;
        }
        public LocalDateTime end() { return start.plusHours(durationHours); }
    }

    public static Reservation lastReservation = null; // يُضبط من ReserveController.confirm()
    // AppState.java
    public static boolean forceEmptyReservations = false;

}
