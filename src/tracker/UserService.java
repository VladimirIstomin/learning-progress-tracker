package tracker;

import java.util.Scanner;

public class UserService {
    private final Scanner scanner;
    private int numberOfStudents;

    public UserService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startCreatingUsers() {
        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equals("back")) {
                break;
            } else if (command.split("\\s+").length < 3) {
                System.out.println("Incorrect credentials.");
                continue;
            }

            try {
                createUser(command);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.printf("Total %d students have been added.\n", numberOfStudents);
    }

    private void createUser(String command) {
        User user = new User();
        user.setFirstName(command.substring(0, command.indexOf(" ")));
        user.setLastName(command.substring(command.indexOf(" ") + 1, command.lastIndexOf(" ")));
        user.setEmail(command.substring(command.lastIndexOf(" ") + 1));
        numberOfStudents++;
        System.out.println("The student has been added.");
    }
}
