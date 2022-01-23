package tracker.daos;

import tracker.CourseSubject;
import java.util.List;
import java.util.Map;

public interface CoursesDao {
    void addAssignment(CourseSubject CourseSubject, Map<String, Integer> assignment);

    List<Integer> getAllAssignmentsByStudentId(CourseSubject CourseSubject, String studentId);
}
