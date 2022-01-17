package tracker;

public interface StudentService {
    void createStudent(String command);

    void showStudentsId();

    void addPoints(String command);

    void showStudentInfo(String command);

    int getNumberOfStudents();
}
