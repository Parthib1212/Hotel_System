import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
//                HotelManager manager = new HotelManager();
//
//        manager.addRoom(new Room(103, "Standard", 100.0));
//        manager.addRoom(new Room(104, "Standard", 100.0));
//        manager.addRoom(new Room(201, "Deluxe", 200.0));
//        manager.addRoom(new Room(202, "Deluxe", 200.0));
//        manager.addRoom(new Room(203, "Deluxe", 220.0));
//        manager.addRoom(new Room(204, "Deluxe", 220.0));
//        manager.addRoom(new Room(302, "Penthouse", 550.0));
//        manager.addRoom(new Room(401, "Suite", 350.0));
//        manager.addRoom(new Room(402, "Suite", 350.0));
//        manager.addRoom(new Room(403, "Executive", 400.0));
//    }
//    }

        DatabaseConfig.initializeDatabase();
        HotelManager manager = new HotelManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Java Hotel Management System!");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Create a Booking");
            System.out.println("3. Check-out of a Room");
            System.out.println("4. Search Guest by Name");
            System.out.println("5. Search Room by Number");
            System.out.println("6. View All Bookings (Report)");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manager.displayAvailableRoom();
                    break;
                case 2:
                    System.out.print("Enter Guest Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Room Number: ");
                    int roomNum = scanner.nextInt();
                    System.out.print("Enter Number of Nights: ");
                    int nights = scanner.nextInt();

                    Guest guest = new Guest(name, "temp@example.com");
                    manager.createBooking(guest, roomNum, nights);
                    break;
                case 3:
                    System.out.print("Enter Room Number for Check-out: ");
                    int checkoutNum = scanner.nextInt();
                    manager.checkout(checkoutNum);
                    break;
                case 4:
                    System.out.print("Enter Guest Name to Search: ");
                    String searchName = scanner.nextLine();
                    manager.searchForGuest(searchName);
                    break;
                case 5:
                    System.out.print("Enter Room Number to Find: ");
                    int findRoomNum = scanner.nextInt();
                    manager.findRoom(findRoomNum);
                    break;
                case 6:
                    manager.displayBookingsReport();
                    break;
                case 0:
                    running = false;
                    System.out.println("System shutting down. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
