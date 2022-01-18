package tracker.services;

public interface StudentService {
    void createStudent(String command);

    void showStudentsId();

    void showStudentInfo(String command);

    int getNumberOfStudents();
}
