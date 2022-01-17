package tracker.controllers;

import tracker.StudentService;

import java.util.Scanner;

public class MainMenuController {
    private final Scanner scanner;
    private final StudentMenuController studentMenuController;
    private final StudentService studentService;

    public MainMenuController(Scanner scanner, StudentMenuController studentMenuController, StudentService studentService) {
        this.scanner = scanner;
        this.studentMenuController = studentMenuController;
        this.studentService = studentService;
    }

    public void start() {
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.length() == 0) {
                System.out.println("No input.");
            } else if (command.equals("add students")) {
                studentMenuController.startAddStudent();
            } else if (command.equals("add points")) {
                studentMenuController.startAddPoints();
            } else if (command.equals("list")) {
                studentService.showStudentsId();
            } else if (command.equals("find")) {
                studentMenuController.startShowStudentInfo();
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
