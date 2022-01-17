package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDaoImplTest {
    private StudentDao studentDao;
    private Student student;

    @BeforeEach
    void doBeforeEach() {
        studentDao = new StudentDaoImpl();
        student = new Student() {};
    }

    @Test
    void addStudent() {
        studentDao.addStudent(student);
        assertEquals(1, studentDao.getNumberOfStudents());
    }

    @Test
    void addStudentWithSameEmail() {
        studentDao.addStudent(student);
        assertThrows(RuntimeException.class, () -> studentDao.addStudent(student));
    }

    @Test
    void getStudentsIdEmpty() {
        assertEquals(0, studentDao.getStudentsId().size());
    }

    @Test
    void getStudentsIdNotEmpty() {
        studentDao.addStudent(student);
        assertEquals(1, studentDao.getStudentsId().size());
    }

    @Test
    void getStudentByIdNotFound() {
        assertTrue(studentDao.getStudentById("42").isEmpty());
        assertTrue(studentDao.getStudentById("0").isEmpty());
    }

    @Test
    void getStudentByIdFound() {
        studentDao.addStudent(student);
        assertTrue(studentDao.getStudentById("0").isPresent());
    }
}