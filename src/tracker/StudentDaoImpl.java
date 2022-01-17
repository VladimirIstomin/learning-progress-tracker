package tracker;

import java.util.*;

public class StudentDaoImpl implements StudentDao {
    private final Map<String, Student> students;

    public StudentDaoImpl() {
        students = new HashMap<>();
    }

    @Override
    public void addStudent(Student student) {
        Optional<Student> existingStudentWithSameEmail = students
                .values()
                .stream()
                .filter(existingStudent -> existingStudent.getEmail().equals(student.getEmail()))
                .findAny();

        if (existingStudentWithSameEmail.isPresent()) {
            throw new RuntimeException("This email is already taken.");
        } else {
            students.put(String.valueOf(students.size()), student);
        }
    }

    @Override
    public List<String> getStudentsId() {
        return new ArrayList<>(students.keySet());
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public int getNumberOfStudents() {
        return students.size();
    }
}
