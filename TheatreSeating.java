import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TheatreSeating {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 2D Array of seats
        String[][] seatArray = {
            {"A", "1"}, {"A", "2"}, {"A", "3"}, {"A", "4"}, {"A", "5"}, {"A", "6"},
            {"B", "1"}, {"B", "2"}, {"B", "3"}, {"B", "4"}, {"B", "5"}, {"B", "6"},
            {"C", "1"}, {"C", "2"}, {"C", "3"}, {"C", "4"}, {"C", "5"}, {"C", "6"},
            {"D", "1"}, {"D", "2"}, {"D", "3"}, {"D", "4"}, {"D", "5"}, {"D", "6"},
            {"E", "1"}, {"E", "2"}, {"E", "3"}, {"E", "4"}, {"E", "5"}, {"E", "6"},
            {"F", "1"}, {"F", "2"}, {"F", "3"}, {"F", "4"}, {"F", "5"}, {"F", "6"}
        };

        // seat code list like "A1"
        List<String> validSeats = new ArrayList<>();
        for (String[] seat : seatArray) {
            validSeats.add(seat[0] + seat[1]);
        }

        List<String> bookedSeats = new ArrayList<>();

        System.out.println("===============================================");
        System.out.println("  Welcome to Galaxy Movie Theatre Booking ");
        System.out.println("===============================================");

        while (true) {
            System.out.println("\n------------------- MENU ----------------------");
            System.out.println("1️  DISPLAY   - Show current seat chart");
            System.out.println("2️  BOOK      - Enter a seat number (e.g. A3)");
            System.out.println("3️  CANCEL    - Type 'REMOVE <seat>' (e.g. REMOVE C4)");
            System.out.println("4️  EXIT      - Type 'EXIT' to quit");
            System.out.println("------------------------------------------------");
            System.out.print(" Enter your command: ");

            String command = input.nextLine().trim().toUpperCase();
            System.out.println();

            if (command.equals("EXIT")) {
                System.out.println(" Goodbye! Thank you for using the system.");
                break;
            }

            if (command.equals("DISPLAY")) {
                System.out.println(" Here are the seats to choose from: ");
                char currentRow = ' ';
                for (String[] seat : seatArray) {
                    String seatCode = seat[0] + seat[1];

                    if (seat[0].charAt(0) != currentRow) {
                        if (currentRow != ' ') System.out.println();
                        currentRow = seat[0].charAt(0);
                    }

                    if (bookedSeats.contains(seatCode)) {
                        System.out.print(" X  ");
                    } else {
                        System.out.printf("%-3s ", seatCode);
                    }
                }
                System.out.println();
                continue;
            }

            if (command.startsWith("REMOVE ")) {
                String cancelSeat = command.substring(7).trim();
                if (!validSeats.contains(cancelSeat)) {
                    System.out.println(" " + cancelSeat + " is not a valid seat.");
                } else if (!bookedSeats.contains(cancelSeat)) {
                    System.out.println("  Seat " + cancelSeat + " is not currently booked.");
                } else {
                    bookedSeats.remove(cancelSeat);
                    System.out.println(" Seat " + cancelSeat + " has been successfully cancelled.");
                }
                continue;
            }

            // Booking a seat
            if (!validSeats.contains(command)) {
                System.out.println(" Invalid seat. Please enter a valid seat code like A1, B4.");
            } else if (bookedSeats.contains(command)) {
                System.out.println(" Sorry, seat " + command + " is already booked.");
                // Suggest available seat
                for (String seat : validSeats) {
                    if (!bookedSeats.contains(seat)) {
                        System.out.println(" Suggested available seat: " + seat);
                        break;
                    }
                }
            } else {
                bookedSeats.add(command);
                System.out.println(" Seat " + command + " has been successfully booked. Enjoy the show!");
            }
        }

        input.close();
    }
}
