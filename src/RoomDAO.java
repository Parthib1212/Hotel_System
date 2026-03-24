import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDAO {
    public void insertRoom(Room room) {
        String sql = "INSERT INTO rooms(room_number, type, price, is_occupied) VALUES(?,?,?,?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, room.getRoomNumber());
            pstmt.setString(2, room.getType());
            pstmt.setDouble(3, room.getPrice());
            pstmt.setInt(4, room.isOccupied() ? 1 : 0);

            pstmt.executeUpdate();
            System.out.println("Room " + room.getRoomNumber() + " saved to database.");
        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
        }

    }

    public static List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Map the database columns back to Java variables
                int roomNum = rs.getInt("room_number");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                int occupiedInt = rs.getInt("is_occupied");

                Room room = new Room(roomNum, type, price);
                room.setOccupied(occupiedInt == 1); // Convert 1 back to true, 0 to false

                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
        }
        return rooms;

    }
    public void updateRoomStatus(int roomNumber, boolean isOccupied) {

        int occupiedStatus = isOccupied ? 1 : 0;
        String sql = "UPDATE rooms SET is_occupied = ? WHERE room_number = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setInt(1, occupiedStatus);
            pstmt.setInt(2, roomNumber);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Database Updated: Room " + roomNumber + " is now " +
                        (isOccupied ? "Occupied" : "Available"));
            } else {
                System.out.println("Update Failed: Room " + roomNumber + " not found.");
            }

        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }
    public static Room findRoomByNumber(int roomNumber) {
        String sql = "SELECT * FROM rooms WHERE room_number = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Room room = new Room(
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getDouble("price")
                );
                room.setOccupied(rs.getInt("is_occupied") == 1);
                return room;
            }
        } catch (SQLException e) {
            System.out.println("Search Error: " + e.getMessage());
        }
        return null; // Return null if the room isn't found
    }

}





