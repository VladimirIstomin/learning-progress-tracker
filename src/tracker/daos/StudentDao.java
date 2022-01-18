package tracker.daos;

import tracker.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    void addStudent(Student student);

    List<String> getStudentsId();

    Optional<Student> getStudentById(String id);

    int getNumberOfStudents();
}
