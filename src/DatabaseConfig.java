import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    private static final String URL = "jdbc:sqlite:hotel.db";

    public DatabaseConfig() throws SQLException {
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL);
    }
    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS rooms (" +
                "room_number INTEGER PRIMARY KEY, " +
                "type TEXT, " +
                "price REAL, " +
                "is_occupied INTEGER DEFAULT 0);";
        String createBookingTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                "booking_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "guest_name TEXT, " +
                "room_number INTEGER, " +
                "nights INTEGER, " +
                "total_price REAL, " +
                "FOREIGN KEY (room_number) REFERENCES rooms(room_number));";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            stmt.execute(createBookingTable);
            System.out.println("SQLite initialized.");


    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






}
