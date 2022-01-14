package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        while (true) {
            String command = scanner.nextLine();
            if (command.trim().length() == 0) {
                System.out.println("No input.");
            } else if (command.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Error: unknown command!");
            }
        }
    }
}
