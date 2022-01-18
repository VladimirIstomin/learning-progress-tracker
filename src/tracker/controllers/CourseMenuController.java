package tracker.controllers;

import tracker.services.CourseService;
import tracker.services.StudentService;

import java.util.Scanner;

public class CourseMenuController {
    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;

    public CourseMenuController(Scanner scanner, StudentService studentService, CourseService courseService) {
        this.scanner = scanner;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void startAddPoints() {
        System.out.println("Enter an id and points or 'back' to return:");

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equals("back")) {
                break;
            }

            try {
                courseService.addPoints(command);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Incorrect points format.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void start() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equals("back")) {
                break;
            }
        }
    }
}
