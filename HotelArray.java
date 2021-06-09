package arrays;

import java.io.*;
import java.util.*;

public class HotelArray {

    private static final Scanner USER_INPUT = new Scanner(System.in);
    private static String[] hotel = new String[9];

    public static void main(String[] args) {

        initialise(hotel);
        String selectedOption;

        consoleMenu:
        while (true) {
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
            System.out.println("O: View Guests In Alphabet Order");
            System.out.println("q: To quit");
            System.out.println();
            System.out.print("Please pick the service you require : ");

            selectedOption = USER_INPUT.next().toUpperCase();
            USER_INPUT.nextLine();

            System.out.println();

            switch (selectedOption) {
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

    private static void initialise(String[] hotelRef) {
        for (int x = 0; x < 8; x++) hotelRef[x] = "e";
        System.out.println("Initializing Data . . . . . . 100%");
    }

    private static void addCustomerToRoom() {

        while (true) {
            System.out.print("Enter room number (0-7) or 8 to stop: ");
            int roomNum = USER_INPUT.nextInt();

            if (roomNum > 7 || roomNum < 0)
                break;

            if (hotel[roomNum].equals("e")) {
                System.out.print("Enter name for room " + roomNum + " : ");
                String roomName = USER_INPUT.next().toLowerCase();
                hotel[roomNum] = roomName;
                break;
            } else {
                System.out.println("The room is currently unavailable. Please try another room");
            }
        }

    }

    private static void viewAllRooms() {
        for (int x = 0; x < 8; x++) {
            if (hotel[x].equals("e")) {
                System.out.println("Room " + x + " is currently vacant");
            } else {
                System.out.println("Room " + x + " is currently occupied by " + hotel[x]);
            }
        }
    }

    private static void emptyRooms() {
        System.out.println();
        System.out.println("=======================================");
        System.out.println("          Available Rooms");
        System.out.println("=======================================");
        for (int x = 0; x < 8; x++) {
            if (hotel[x].equals("e"))
                System.out.println("Room number " + x + " is empty");
        }
    }

    private static void removeCustomer() {

        int roomNum;
        while (true){
            System.out.print("Enter a room number (0-7): ");
            roomNum = USER_INPUT.nextInt();
            if (roomNum > 7 || roomNum < 0)
                System.out.println("Room Not Available. \n");
            else {
                break;
            }
        }

        if (hotel[roomNum].equals("e")) {
            System.out.println("Room is Not Booked.");
        } else {
            String customer = hotel[roomNum];
            hotel[roomNum] = "e";
            System.out.println("Customer " + customer +" Removed From Room Successfully");
        }

    }

    private static void findRoom() {
        System.out.print("Enter customer name : ");
        String customerName = USER_INPUT.next().toLowerCase();

        for (int x = 0; x < 8; x++) {
            if (hotel[x].equals(customerName) ) {
                System.out.println("Customer has booked room " + x);
            }
        }
    }

    private static void storeData() {

        File dataFile = new File("hotelRooms.txt");
        try {
            FileOutputStream fileOutput= new FileOutputStream(dataFile);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(hotel);

            fileOutput.close();
            objectOutput.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Data Has Been Saved successfully!");

    }

    private static void loadData() {

        File dataFile = new File("hotelRooms.txt");

        if(dataFile.exists()) {

            try {
                FileInputStream fileInput = new FileInputStream(dataFile);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);

                String[] hotelArr = (String[]) objectInput.readObject();
                hotel = hotelArr;

                fileInput.close();
                objectInput.close();

                System.out.println("Data Loaded successfully! ");

            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            System.out.println(" Sorry ! File Not Found ");
        }
    }

    private static void viewGuests() {

        System.out.println("=======================================");
        System.out.println("       Customers in alphabet order");
        System.out.println("=======================================");
        System.out.println();

        int size = hotel.length - 1;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < hotel.length; j++) {
                if (hotel[i] != null && hotel[j] != null) {
                    if (hotel[i].compareTo(hotel[j]) > 0) {
                        String temp = hotel[i];
                        hotel[i] = hotel[j];
                        hotel[j] = temp;
                    }
                }
            }
        }

        for (int x = 0; x < 6; x++) {
            if (!hotel[x].equals("e")) {
                System.out.println("Customer name : " + hotel[x]);
            }
        }
    }
}