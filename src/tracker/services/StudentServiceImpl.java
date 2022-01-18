package tracker.services;

import tracker.daos.CoursesDao;
import tracker.Student;
import tracker.daos.StudentDao;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final CoursesDao coursesDao;

    public StudentServiceImpl(StudentDao studentDao, CoursesDao coursesDao) {
        this.studentDao = studentDao;
        this.coursesDao = coursesDao;
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
    public void showStudentInfo(String command) {
        Optional<Student> studentOptional = studentDao.getStudentById(command);
        if (studentOptional.isEmpty()) {
            System.out.println("No student is found for id=" + command + ".");
        } else {
            System.out.printf(
                    "%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n",
                    command,
                    sumAssignmentsPoints(coursesDao.getAllAssignmentsByStudentId("Java", command)),
                    sumAssignmentsPoints(coursesDao.getAllAssignmentsByStudentId("DSA", command)),
                    sumAssignmentsPoints(coursesDao.getAllAssignmentsByStudentId("Databases", command)),
                    sumAssignmentsPoints(coursesDao.getAllAssignmentsByStudentId("Spring", command)));
        }
    }

    private int sumAssignmentsPoints(List<Integer> assignments) {
        return assignments.stream().mapToInt(i -> i).sum();
    }

    @Override
    public int getNumberOfStudents() {
        return studentDao.getNumberOfStudents();
    }
}
