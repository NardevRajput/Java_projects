import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a Room
class Room {
    int roomNumber;
    String type;
    double price;
    boolean isBooked;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Type: " + type + ", Price: $" + price + ", Booked: " + (isBooked ? "Yes" : "No");
    }
}

public class HotelManagementSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. Release Room");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addRoom();
                    break;
                case 2:
                    viewRooms();
                    break;
                case 3:
                    bookRoom();
                    break;
                case 4:
                    releaseRoom();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    static void addRoom() {
        System.out.print("Enter room number: ");
        int number = scanner.nextInt();
        System.out.print("Enter room type (Single/Double/Suite): ");
        String type = scanner.next();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Room room = new Room(number, type, price);
        rooms.add(room);
        System.out.println("Room added successfully!");
    }

    static void viewRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    static void bookRoom() {
        System.out.print("Enter room number to book: ");
        int number = scanner.nextInt();
        for (Room room : rooms) {
            if (room.roomNumber == number) {
                if (!room.isBooked) {
                    room.isBooked = true;
                    System.out.println("Room booked successfully!");
                } else {
                    System.out.println("Room is already booked.");
                }
                return;
            }
        }
        System.out.println("Room not found.");
    }

    static void releaseRoom() {
        System.out.print("Enter room number to release: ");
        int number = scanner.nextInt();
        for (Room room : rooms) {
            if (room.roomNumber == number) {
                if (room.isBooked) {
                    room.isBooked = false;
                    System.out.println("Room released successfully!");
                } else {
                    System.out.println("Room is not booked.");
                }
                return;
            }
        }
        System.out.println("Room not found.");
    }
}