public class Booking {

    private static int idCounter = 1000;
    private int bookingId;
    private Guest guest;
    private Room room;
    private int nights;

    public Booking(Guest guest, Room room, int nights) {
        this.guest = guest;
        this.room = room;
        this.nights = nights;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public double calculateTotal(){
        return  room.getPrice()* nights;
    }

    @Override
    public String toString() {
        return "Booking #" + bookingId + " | Guest: " + guest.getName() +
                " | Room: " + room.getRoomNumber() + " | Nights: " + nights +
                " | Total: $" + calculateTotal();
    }
}
