package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Course {
    private final List<Map<String, Integer>> assignments;

    public Course() {
        assignments = new ArrayList<>();
    }

    public void addAssignment(Map<String, Integer> assignment) {
        if (assignment.values().stream().findFirst().orElseThrow() < 0) {
            throw new RuntimeException("Incorrect points format.");
        }
        assignments.add(assignment);
    }

    public List<Integer> getAllAssignmentsByStudentId(String studentId) {
        return assignments.stream()
                .filter(assignment -> assignment.containsKey(studentId))
                .map(assignment -> assignment.get(studentId))
                .collect(Collectors.toList());
    }
}
