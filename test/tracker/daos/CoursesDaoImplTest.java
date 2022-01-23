package tracker.daos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.CourseSubject;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CoursesDaoImplTest {
    CoursesDao coursesDao;
    Map<String, Integer> incorrectAssignment;
    Map<String, Integer> correctAssignment;

    @BeforeEach
    void setUp() {
        coursesDao = new CoursesDaoImpl();
        incorrectAssignment = Map.of("0", -1);
        correctAssignment = Map.of("0", 42);
    }

    @Test
    void addAssignmentsIncorrect() {
        assertThrows(RuntimeException.class, () -> coursesDao.addAssignment(CourseSubject.JAVA, incorrectAssignment));
        assertThrows(RuntimeException.class, () -> coursesDao.addAssignment(null, correctAssignment)); // no such course
    }

    @Test
    void addAssignmentsAndGetThemByStudentId() {
        for (int i = 0; i < 10; i++) {
            coursesDao.addAssignment(CourseSubject.JAVA, correctAssignment);
        }
        coursesDao.addAssignment(CourseSubject.DSA, correctAssignment);
        for (int i = 0; i < 42; i++) {
            coursesDao.addAssignment(CourseSubject.DATABASES, correctAssignment);
            coursesDao.addAssignment(CourseSubject.SPRING, correctAssignment);
        }
        assertEquals(0, coursesDao.getAllAssignmentsByStudentId(CourseSubject.JAVA, "42").size()); // no assignment with such student id
        assertEquals(10, coursesDao.getAllAssignmentsByStudentId(CourseSubject.JAVA, "0").size());
        assertEquals(1, coursesDao.getAllAssignmentsByStudentId(CourseSubject.DSA,"0").size());
        assertEquals(42, coursesDao.getAllAssignmentsByStudentId(CourseSubject.DATABASES, "0").size());
        assertEquals(42, coursesDao.getAllAssignmentsByStudentId(CourseSubject.SPRING, "0").size());
    }
}