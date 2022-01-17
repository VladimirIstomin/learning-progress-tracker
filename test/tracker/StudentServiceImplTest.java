package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {
    StudentDao studentDao;
    Student testStudent;
    StudentService studentService;

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

        studentService = new StudentServiceImpl(studentDao);
    }

    @Test
    void createStudentFromCommandNotEnoughParameters() {
        assertThrows(RuntimeException.class, () -> studentService.createStudent("hello world"));
    }

    @Test
    void createStudentFromCommandWithOneSpaceSeparator() {
        studentService.createStudent("Hello World hello@world.com");
        assertEquals("Hello", testStudent.getFirstName());
        assertEquals("World", testStudent.getLastName());
        assertEquals("hello@world.com", testStudent.getEmail());
    }

    @Test
    void createStudentFromCommandWithMultipleSpacesSeparator() {
        studentService.createStudent("Hello  World    hello@world.com");
        assertEquals("Hello", testStudent.getFirstName());
        assertEquals("World", testStudent.getLastName());
        assertEquals("hello@world.com", testStudent.getEmail());
    }

    @Test
    void showStudentsIdEmpty() {
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        studentService.showStudentsId();
        assertEquals("No students found.\n", byteArrayOutputStream.toString());
        System.setOut(out);
    }

    @Test
    void showStudentsNotEmpty() {
        studentService.createStudent("Hello World hello@world.com");
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        studentService.showStudentsId();
        assertEquals("Students:\n1\n", byteArrayOutputStream.toString());
        System.setOut(out);
    }

    @Test
    void addPointsWrongFormat() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> studentService.addPoints("1 2 3")); // not enough parameters
        assertThrows(RuntimeException.class, () -> studentService.addPoints("1 2 3 4 5 6")); // too many parameters
        assertThrows(NumberFormatException.class, () -> studentService.addPoints("a b c")); // parameters not integers
    }

    @Test
    void addPointNotFoundStudentId() { // a student with id '42' has been created yet
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        studentService.addPoints("42 1 2 3 4");
        assertEquals("No student is found for id=42.\n", byteArrayOutputStream.toString());
        System.setOut(out);
    }

    @Test
    void addPointsCorrect() {
        studentService.createStudent("Hello World hello@world.com");
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        studentService.addPoints("0 1 2 3 4");
        assertEquals("Points updated.\n", byteArrayOutputStream.toString());
        System.setOut(out);
    };

    @Test
    void showStudentInfoNotFoundStudentId() {
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        studentService.showStudentInfo("42");
        assertEquals("No student is found for id=42.\n", byteArrayOutputStream.toString());
        System.setOut(out);
    }

    @Test
    void showStudentInfoCorrect() {
        studentService.createStudent("Hello World hello@world.com");
        PrintStream out = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        studentService.showStudentInfo("0");
        assertEquals("0 points: Java=0; DSA=0; Databases=0; Spring=0\n", byteArrayOutputStream.toString());
        System.setOut(out);
    }
}