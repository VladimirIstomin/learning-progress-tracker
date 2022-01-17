package tracker;

import tracker.controllers.MainMenuController;
import tracker.controllers.StudentMenuController;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        Scanner scanner = new Scanner(System.in);
        StudentDao studentDao = new StudentDaoImpl();
        StudentService studentService = new StudentServiceImpl(studentDao);
        StudentMenuController studentMenuController = new StudentMenuController(scanner, studentService);
        MainMenuController mainMenuController = new MainMenuController(scanner, studentMenuController, studentService);

        mainMenuController.start();
    }
}
