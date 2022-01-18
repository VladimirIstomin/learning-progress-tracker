package tracker.daos;

import tracker.Course;

import java.util.List;
import java.util.Map;

public class CoursesDaoImpl implements CoursesDao {
    private final Map<String, Course> courses;

    public CoursesDaoImpl() {
        courses = Map.of(
                "Java", new Course(),
                "DSA", new Course(),
                "Databases", new Course(),
                "Spring", new Course()

        );
    }

    @Override
    public void addAssignment(String courseName, Map<String, Integer> assignment) {
        if (!courses.containsKey(courseName)) {
            throw new RuntimeException("Unknown course.");
        } else {
            courses.get(courseName).addAssignment(assignment);
        }
    }

    @Override
    public List<Integer> getAllAssignmentsByStudentId(String courseName, String studentId) {
        if (!courses.containsKey(courseName)) {
            throw new RuntimeException("Unknown course.");
        } else {
            return courses.get(courseName).getAllAssignmentsByStudentId(studentId);
        }
    }
}
