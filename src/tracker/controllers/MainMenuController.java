package tracker.controllers;

import tracker.services.StudentService;

import java.util.Scanner;

public class MainMenuController {
    private final Scanner scanner;
    private final StudentMenuController studentMenuController;
    private final StudentService studentService;
    private final CourseMenuController courseMenuController;

    public MainMenuController(Scanner scanner, StudentMenuController studentMenuController,
                              StudentService studentService, CourseMenuController courseMenuController) {
        this.scanner = scanner;
        this.studentMenuController = studentMenuController;
        this.studentService = studentService;
        this.courseMenuController = courseMenuController;
    }

    public void start() {
        while (true) {
            String command = scanner.nextLine().trim();

            if (command.length() == 0) {
                System.out.println("No input.");
            } else if (command.equals("add students")) {
                studentMenuController.startAddStudent();
            } else if (command.equals("add points")) {
                courseMenuController.startAddPoints();
            } else if (command.equals("list")) {
                studentService.showStudentsId();
            } else if (command.equals("find")) {
                studentMenuController.startShowStudentInfo();
            } else if (command.equals("statistics")) {
                courseMenuController.start();
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
