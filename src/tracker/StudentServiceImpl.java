package tracker;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void createStudent(String command) {
        if (command.split("\\s+").length < 3) {
            throw new RuntimeException("Incorrect credentials.");
        }

        Student student = new Student();
        student.setFirstName(command.substring(0, command.indexOf(" ")));
        student.setLastName(command.substring(command.indexOf(" "), command.lastIndexOf(" ")).trim());
        student.setEmail(command.substring(command.lastIndexOf(" ") + 1));
        studentDao.addStudent(student);

        System.out.println("The student has been added.");
    }

    @Override
    public void showStudentsId() {
        List<String> studentsId = studentDao.getStudentsId();
        if (studentsId.size() > 0) {
            System.out.println("Students:");
            studentsId.forEach(System.out::println);
        } else {
            System.out.println("No students found.");
        }
    }

    @Override
    public void addPoints(String command) {
        String[] updatePointsData = command.split("\\s+");
        if (updatePointsData.length > 5) {
            throw new RuntimeException("Incorrect points format.");
        }
        String id = updatePointsData[0];
        int javaPoints = Integer.parseInt(updatePointsData[1]);
        int dsaPoints = Integer.parseInt(updatePointsData[2]);
        int databasesPoints = Integer.parseInt(updatePointsData[3]);
        int springPoints = Integer.parseInt(updatePointsData[4]);

        Optional<Student> studentOptional = studentDao.getStudentById(id);
        if (studentOptional.isEmpty()) {
            System.out.println("No student is found for id=" + id + ".");
        } else {
            Student student = studentOptional.get();
            student.addPoints(javaPoints, dsaPoints, databasesPoints, springPoints);
            System.out.println("Points updated.");
        }
    }

    @Override
    public void showStudentInfo(String command) {
        Optional<Student> studentOptional = studentDao.getStudentById(command);
        if (studentOptional.isEmpty()) {
            System.out.println("No student is found for id=" + command + ".");
        } else {
            Student student = studentOptional.get();
            System.out.printf(
                    "%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n",
                    command,
                    student.getJavaPoints(),
                    student.getDsaPoints(),
                    student.getDatabasesPoints(),
                    student.getSpringPoints());
        }
    }

    @Override
    public int getNumberOfStudents() {
        return studentDao.getNumberOfStudents();
    }
}
