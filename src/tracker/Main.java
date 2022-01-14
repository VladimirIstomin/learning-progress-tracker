package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.length() == 0) {
                System.out.println("No input.");
            } else if (command.equals("add students")) {
                System.out.println("Enter student credentials or 'back' to return:");
                UserService userService = new UserService(scanner);
                userService.startCreatingUsers();
            } else if (command.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (command.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");
            } else {
                System.out.println("Unknown command!");
            }
        }
    }
}
