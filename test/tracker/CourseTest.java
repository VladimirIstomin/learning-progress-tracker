package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    Course course;

    @BeforeEach
    void setUp() {
        course = new Course();
    }

    @Test
    void addAssignmentWithNegativeScore() {
        assertThrows(RuntimeException.class, () -> course.addAssignment(Map.of("hello", -1)));
    }

    @Test
    void getAllAssignmentsByStudentIdNotExisting() {
        assertEquals(0, course.getAllAssignmentsByStudentId("hello").size());
    }

    @Test
    void getAllAssignmentsByStudentIdExisting() {
        course.addAssignment(Map.of("hello", 10));
        assertEquals(1, course.getAllAssignmentsByStudentId("hello").size());
        course.addAssignment(Map.of("world", 10));
        assertEquals(1, course.getAllAssignmentsByStudentId("hello").size());
    }
}