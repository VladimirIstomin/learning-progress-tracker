package tracker.controllers;

import tracker.services.StudentService;

import java.util.Scanner;

public class StudentMenuController {
    private final Scanner scanner;
    private final StudentService studentService;

    public StudentMenuController(Scanner scanner, StudentService studentService) {
        this.scanner = scanner;
        this.studentService = studentService;
    }

    public void startAddStudent() {
        System.out.println("Enter student credentials or 'back' to return:");

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equals("back")) {
                break;
            }

            try {
                studentService.createStudent(command);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.printf("Total %d students have been added.\n", studentService.getNumberOfStudents());
    }

    public void startShowStudentInfo() {
        System.out.println("Enter an id or 'back' to return:");

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equals("back")) {
                break;
            }

            try {
                studentService.showStudentInfo(command);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
