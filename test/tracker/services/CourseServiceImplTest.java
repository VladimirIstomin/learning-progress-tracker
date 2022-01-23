package tracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.CourseSubject;
import tracker.daos.CoursesDao;
import tracker.Student;
import tracker.daos.StudentDao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceImplTest {
    StudentDao studentDao;
    CoursesDao coursesDao;
    Student testStudent;
    CourseService courseService;

    @BeforeEach
    void setUp() {
        studentDao = new StudentDao() {
            @Override
            public void addStudent(Student student) {
                testStudent = student;
            }

            @Override
            public List<String> getStudentsId() {
                if (testStudent != null) {
                    return List.of("1");
                } else {
                    return List.of();
                }
            }

            @Override
            public Optional<Student> getStudentById(String id) {
                return Optional.ofNullable(testStudent);
            }

            @Override
            public int getNumberOfStudents() {
                return 0;
            }
        };
        coursesDao = new CoursesDao() {

            @Override
            public void addAssignment(CourseSubject CourseSubject, Map<String, Integer> assignment) {

            }

            @Override
            public List<Integer> getAllAssignmentsByStudentId(CourseSubject CourseSubject, String studentId) {
                return null;
            }
        };

        courseService = new CourseServiceImpl(studentDao, coursesDao);
    }

    @Test
    void addPointsWrongFormat() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> courseService.addPoints("1 2 3")); // not enough parameters
        assertThrows(RuntimeException.class, () -> courseService.addPoints("1 2 3 4 5 6")); // too many parameters
        assertThrows(NumberFormatException.class, () -> courseService.addPoints("a b c")); // parameters not integers
    }

    @Test
    void addPointNotFoundStudentId() { // a student with id '42' has been created yet
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        courseService.addPoints("42 1 2 3 4");
        assertEquals("No student is found for id=42.\n", byteArrayOutputStream.toString());
        System.setOut(out);
    }

    @Test
    void addPointsCorrect() {
        studentDao.addStudent(new Student());
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        courseService.addPoints("0 1 2 3 4");
        assertEquals("Points updated.\n", byteArrayOutputStream.toString());
        System.setOut(out);
    };
}