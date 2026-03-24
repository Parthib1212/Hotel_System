public class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean isOccupied;

    public Room(int roomNumber, String type, double price){
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isOccupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
    public boolean isOccupied() {
        return isOccupied;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    @Override
    public String toString() {
        return "Room #" + roomNumber + " | Type: " + type + " | Price: $" + price + " | Status: " + (isOccupied ? "Booked" : "Available");
    }
}


