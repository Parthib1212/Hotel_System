import java.sql.*;
import java.util.ArrayList;

public class BookingDAO {


    public static void saveBooking(String guestName, int roomNumber, int nights, double total) throws SQLException {
        String sql = "insert into bookings(guest_name, room_number, nights,total_price) values(?,?,?,?)";

        try (Connection conn = DatabaseConfig.getConnection()
        ;PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, guestName);
            pstmt.setInt(2, roomNumber);
            pstmt.setInt(3, nights);
            pstmt.setDouble(4, total);
            
            pstmt.executeUpdate();
            System.out.println("Booking record saved!!");
            
        } catch (RuntimeException e) {
            System.out.println("Booking DB error" + e.getMessage());
        }
    }
    public static void listAllBookings() {
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- All Booking Records ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("booking_id") +
                        " | Guest: " + rs.getString("guest_name") +
                        " | Room: " + rs.getInt("room_number") +
                        " | Total: $" + rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
    }
    public static void searchBookingsByGuest(String name) {

        String sql = "SELECT * FROM bookings WHERE guest_name LIKE ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- Search Results for: " + name + " ---");
            boolean found = false;
            while (rs.next()) {
                System.out.println("Booking #" + rs.getInt("booking_id") +
                        " | Guest: " + rs.getString("guest_name") +
                        " | Room: " + rs.getInt("room_number") +
                        " | Total: $" + rs.getDouble("total_price"));
                found = true;
            }

            if (!found) System.out.println("No bookings found for that name.");

        } catch (SQLException e) {
            System.out.println("Booking Search Error: " + e.getMessage());
        }
    }

}
