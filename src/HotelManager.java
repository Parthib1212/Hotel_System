import com.sun.security.jgss.GSSUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class HotelManager {

    //Änderung nach DatenBanken
    public RoomDAO roomDao;

    public HotelManager(){
        this.roomDao = new RoomDAO();
    }

    public void addRoom(Room r){
        roomDao.insertRoom(r);
    }

    public void displayAvailableRoom(){
        System.out.println("\n Available room :");
        for(Room r : roomDao.getAllRooms()){
            if(!r.isOccupied()){
                System.out.println(r.toString());
            }
        }
    }
    public void bookRoom(int roomNumber){
        roomDao.updateRoomStatus(roomNumber, true);
        System.out.println("Room " + roomNumber+ "  booking sent to DB");
    }

    public void checkout(int roomNumber){
        roomDao.updateRoomStatus(roomNumber, false);
        System.out.println("Check out Successful for Room :" + roomNumber);
    }
    private ArrayList<Booking> activeBookings = new ArrayList<>();


    public void createBooking(Guest guest, int roomNumber, int nights) throws SQLException {
        Room roomToBook = null;
        for (Room r : RoomDAO.getAllRooms()){
            if(r.getRoomNumber() == roomNumber && !r.isOccupied()){
                roomToBook = r;
                break;
            }
        }
        if(roomToBook != null){
            double total = roomToBook.getPrice() + nights;

            BookingDAO.saveBooking(guest.getName(), roomNumber, nights, total);
            roomDao.updateRoomStatus(roomNumber, true);

            System.out.println("Booking complete for " + guest.getName());
        }
        else {
            System.out.println("Error: No room available");
        }
    }
    public void displayBookingsReport() {
        BookingDAO.listAllBookings();
    }
    public void searchForGuest(String name) {
        BookingDAO.searchBookingsByGuest(name);
    }

    public void findRoom(int roomNumber) {
        Room r = RoomDAO.findRoomByNumber(roomNumber);
        if (r != null) {
            System.out.println("Found: " + r);
        } else {
            System.out.println("Room " + roomNumber + " does not exist.");
        }
    }


}
