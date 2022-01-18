package tracker.daos;

import java.util.List;
import java.util.Map;

public interface CoursesDao {
    void addAssignment(String courseName, Map<String, Integer> assignment);

    List<Integer> getAllAssignmentsByStudentId(String courseName, String studentId);
}
