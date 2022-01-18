package tracker.daos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CoursesDaoImplTest {
    CoursesDao coursesDao;
    Map<String, Integer> incorrectAssignment = Map.of("0", -1);
    Map<String, Integer> correctAssignment = Map.of("0", 42);

    @BeforeEach
    void setUp() {
        coursesDao = new CoursesDaoImpl();
    }

    @Test
    void addAssignmentsIncorrect() {
        assertThrows(RuntimeException.class, () -> coursesDao.addAssignment("Java", incorrectAssignment));
        assertThrows(RuntimeException.class, () -> coursesDao.addAssignment("hello", correctAssignment)); // no such course
    }

    @Test
    void addAssignmentsAndGetThemByStudentId() {
        for (int i = 0; i < 10; i++) {
            coursesDao.addAssignment("Java", correctAssignment);
        }
        coursesDao.addAssignment("DSA", correctAssignment);
        for (int i = 0; i < 42; i++) {
            coursesDao.addAssignment("Databases", correctAssignment);
            coursesDao.addAssignment("Spring", correctAssignment);
        }
        assertEquals(0, coursesDao.getAllAssignmentsByStudentId("Java", "42").size()); // no assignment with such student id
        assertEquals(10, coursesDao.getAllAssignmentsByStudentId("Java", "0").size());
        assertEquals(1, coursesDao.getAllAssignmentsByStudentId("DSA","0").size());
        assertEquals(42, coursesDao.getAllAssignmentsByStudentId("Databases", "0").size());
        assertEquals(42, coursesDao.getAllAssignmentsByStudentId("Spring", "0").size());
    }
}