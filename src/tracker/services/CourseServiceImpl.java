package tracker.services;

import tracker.CourseSubject;
import tracker.daos.CoursesDao;
import tracker.Student;
import tracker.daos.StudentDao;

import java.util.Map;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private final StudentDao studentDao;
    private final CoursesDao coursesDao;

    public CourseServiceImpl(StudentDao studentDao, CoursesDao coursesDao) {
        this.studentDao = studentDao;
        this.coursesDao = coursesDao;
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
            coursesDao.addAssignment(CourseSubject.JAVA, Map.of(id, javaPoints));
            coursesDao.addAssignment(CourseSubject.DSA, Map.of(id, dsaPoints));
            coursesDao.addAssignment(CourseSubject.DATABASES, Map.of(id, databasesPoints));
            coursesDao.addAssignment(CourseSubject.SPRING, Map.of(id, springPoints));
            System.out.println("Points updated.");
        }
    }
}
