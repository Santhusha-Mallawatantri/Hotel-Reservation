package classes;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Hotel {

    private static final Scanner USER_INPUT = new Scanner(System.in);
    private static ArrayList<Room> hotel = new ArrayList<Room>();
    private static ArrayList<Room> sortHotel = new ArrayList<Room>();

    private static ArrayList<Room> booked = new ArrayList<Room>();
    private static Queue queue = new Queue();

    public static void main(String[] args) {
        initialise(hotel);
        String option;
        consoleMenu:
        while (true) {
            dashBoard();
            option = USER_INPUT.next().toUpperCase();
            USER_INPUT.nextLine();
            System.out.println();

            switch (option) {
                case "A":
                    addCustomerToRoom();
                    break;

                case "V":
                    viewAllRooms();
                    break;

                case "E":
                    emptyRooms();
                    break;

                case "D":
                    removeCustomer();
                    break;

                case "F":
                    findRoom();
                    break;

                case "S":
                    storeData();
                    break;

                case "L":
                    loadData();
                    break;

                case "O":
                    viewGuests();
                    break;

                case "q":
                    System.out.println("Thank You. Have a wonderful day and we hope to see you again");
                    break consoleMenu;

                default:
                    System.out.println("Option Selected is Invalid. TRY AGAIN !!");

            }
        }

    }

    public static void dashBoard() {
        System.out.println();
        System.out.println("=======================================");
        System.out.println("          Hotel Booking Menu");
        System.out.println("=======================================");

        System.out.println("A: Add Customer To Room");
        System.out.println("V: View All Room");
        System.out.println("E: Display Empty Rooms");
        System.out.println("D: Delete Customer From Room");
        System.out.println("F: Find Room From Customer Name");
        System.out.println("S: Store Program Data Into File");
        System.out.println("L: Load Program Data From File");
        System.out.println("O: View Guests In Order");
        System.out.println("q: To quit");
        System.out.println();
        System.out.print("Please pick the service you require : ");
    }

    private static void addCustomerToRoom() {

        while (true) {
            System.out.print("Enter room number: ");
            int roomNum = USER_INPUT.nextInt();

            if (roomNum < 0 && roomNum > 7) {
                System.out.println("Incorrect Input !!");
                break;

            } else if (booked.size() != 7) {
                for (Room room : booked) {
                    if (room.getRoomNumber() == roomNum) {
                        System.out.println("Room Booked. Try Another Room");
                        System.out.print("Enter room number: ");
                        roomNum = USER_INPUT.nextInt();
                        break;
                    }
                }

                System.out.print("Enter First Name ( Room " + roomNum + " ): ");
                String firstName = USER_INPUT.next().toLowerCase();

                System.out.print("Enter Last Name ( Room " + roomNum + " ): ");
                String lastName = USER_INPUT.next().toLowerCase();

                System.out.print("Enter Credit Card Number: ");
                String creditNo = USER_INPUT.next();

                System.out.print("No of guests: ");
                int noOfGuests = USER_INPUT.nextInt();
                System.out.println("Added Success!!!");

                Person newCustomer = new Person(firstName, lastName, creditNo);
                Room newRoom = new Room(roomNum, noOfGuests, newCustomer);
                hotel.set(roomNum, newRoom);
                booked.add(newRoom);
                break;

            } else {
                System.out.println("All the rooms are booked. Do you want to get add to the queue (y/n) :");
                String anss = USER_INPUT.next();

                if (anss == "y") {
                    System.out.print("Enter First Name ( Room " + roomNum + " ): ");
                    String firstName = USER_INPUT.next().toLowerCase();

                    System.out.print("Enter Last Name ( Room " + roomNum + " ): ");
                    String lastName = USER_INPUT.next().toLowerCase();

                    System.out.print("Enter Credit Card Number: ");
                    String creditNo = USER_INPUT.next();

                    System.out.print("No of guests: ");
                    int noOfGuests = USER_INPUT.nextInt();
                    System.out.println("Added Success!!!");

                    Person newCustomer = new Person(firstName, lastName, creditNo);
                    Room newRoom = new Room(roomNum, noOfGuests, newCustomer);
                    hotel.set(roomNum, newRoom);
                    queue.addingToQueue(newRoom);
                    break;

                } else {
                    System.out.println("Thank You! Come Again");
                }
            }
        }
    }

    private static void viewAllRooms() {

        for (int x = 0; x < hotel.size(); x++) {
            if (hotel.get(x) == null) {
                System.out.println("Room " + x + " is currently vacant");
            } else {
                System.out.println("Room " + x + " is currently occupied by " + hotel.get(x).getPayingGuest().getFirstName());
            }
        }
        toMainMenu();
    }

    private static void emptyRooms() {
        System.out.println();
        System.out.println("=======================================");
        System.out.println("          Available Rooms");
        System.out.println("=======================================");
        for (int x = 0; x < hotel.size(); x++) {
            if (hotel.get(x) == null)
                System.out.println("Room " + x + " is empty");
        }
        toMainMenu();
    }

    private static void removeCustomer() {

        int roomNum;
        while (true) {
            System.out.print("Enter a room number: ");
            roomNum = USER_INPUT.nextInt();
            if (roomNum > 7 || roomNum < 0)
                System.out.println("Room Not Available. \n");
            else {
                break;
            }
        }

        if (hotel.get(roomNum) == null) {
            System.out.println("Room is Not Booked.");
        } else {
            String customer = hotel.get(roomNum).getPayingGuest().getFirstName();
            hotel.set(roomNum, null);
            hotel.set(roomNum, queue.removingFromQueue());
            System.out.println("Customer " + customer + " Removed From Room Successfully");
        }
        toMainMenu();
    }

    private static void findRoom() {

        System.out.print("Enter customer First name : ");
        String customerName = USER_INPUT.next().toLowerCase();

        for (int i = 0; i < 8; i++) {
            try {
                String room = hotel.get(i).getPayingGuest().getFirstName();
                System.out.println("room = " + room);
                if (room.equals(customerName)) {
                    System.out.println("Customer has booked room " + i);
                } else {
                    System.out.println("else");
                }
            } catch (Exception e) {

            }

        }

        toMainMenu();
    }

    private static void storeData() {

        File dataFile = new File("bookings.txt");
        try {
            FileOutputStream fileOutput = new FileOutputStream(dataFile);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(hotel);
            fileOutput.close();
            objectOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Data Has Been Saved successfully!");

    }

    private static void loadData() {

        File dataFile = new File("bookings.txt");
        if (dataFile.exists()) {

            try {
                FileInputStream fileInput = new FileInputStream(dataFile);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);

                hotel = (ArrayList<Room>) objectInput.readObject();
//                System.out.println(hotel);

                fileInput.close();
                objectInput.close();

                System.out.println("Data Loaded successfully! ");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(" Sorry ! File Not Found ");
        }

    }

    private static void viewGuests() {
        for (int i = 0; i < hotel.size(); i++) {
            if (hotel.get(i) != null) {
                sortHotel.add(hotel.get(i));
            }
        }
        for (int i = 0; i < sortHotel.size(); i++) {
            for (int j = i + 1; j < sortHotel.size(); j++) {
                if (sortHotel.get(i).getPayingGuest().getFirstName().compareTo(sortHotel.get(j).getPayingGuest().getFirstName()) > 0) {
                    System.out.println("in the if block");
                    Room temp = sortHotel.get(i);
                    sortHotel.set(i, sortHotel.get(j));
                    sortHotel.set(j, temp);
                }
            }
        }
        sortHotel.forEach(System.out::println);
        toMainMenu();
    }

    public static void toMainMenu() {
        System.out.println();
        System.out.print("Press any key to  goto main Menu : ");
        String a = USER_INPUT.next();
    }

    private static void initialise(ArrayList<Room> rooms) {
        for (int x = 0; x < 8; x++) rooms.add(x, null);
        System.out.println("Initializing Data . . . . 100%");
    }

}
