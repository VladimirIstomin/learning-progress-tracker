package tracker.daos;

import tracker.Course;
import tracker.CourseSubject;

import java.util.List;
import java.util.Map;

public class CoursesDaoImpl implements CoursesDao {
    private final Map<CourseSubject, Course> courses;

    public CoursesDaoImpl() {
        courses = Map.of(
                CourseSubject.JAVA, new Course(),
                CourseSubject.DSA, new Course(),
                CourseSubject.DATABASES, new Course(),
                CourseSubject.SPRING, new Course()

        );
    }

    @Override
    public void addAssignment(CourseSubject CourseSubject, Map<String, Integer> assignment) {
        if (!courses.containsKey(CourseSubject)) {
            throw new RuntimeException("Unknown course.");
        } else {
            courses.get(CourseSubject).addAssignment(assignment);
        }
    }

    @Override
    public List<Integer> getAllAssignmentsByStudentId(CourseSubject CourseSubject, String studentId) {
        if (!courses.containsKey(CourseSubject)) {
            throw new RuntimeException("Unknown course.");
        } else {
            return courses.get(CourseSubject).getAllAssignmentsByStudentId(studentId);
        }
    }
}
