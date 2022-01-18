package tracker;

import tracker.controllers.CourseMenuController;
import tracker.controllers.MainMenuController;
import tracker.controllers.StudentMenuController;
import tracker.daos.CoursesDao;
import tracker.daos.CoursesDaoImpl;
import tracker.daos.StudentDao;
import tracker.daos.StudentDaoImpl;
import tracker.services.CourseService;
import tracker.services.CourseServiceImpl;
import tracker.services.StudentService;
import tracker.services.StudentServiceImpl;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        Scanner scanner = new Scanner(System.in);

        StudentDao studentDao = new StudentDaoImpl();
        CoursesDao coursesDao = new CoursesDaoImpl();

        StudentService studentService = new StudentServiceImpl(studentDao, coursesDao);
        CourseService courseService = new CourseServiceImpl(studentDao, coursesDao);

        StudentMenuController studentMenuController = new StudentMenuController(scanner, studentService);
        CourseMenuController courseMenuController = new CourseMenuController(scanner, studentService, courseService);
        MainMenuController mainMenuController = new MainMenuController(scanner, studentMenuController, studentService, courseMenuController);

        mainMenuController.start();
    }
}
