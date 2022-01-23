package tracker;

import java.util.Arrays;
import java.util.Optional;

public enum CourseSubject {
    JAVA("Java"),
    DSA("DSA"),
    DATABASES("Databases"),
    SPRING("Spring");

    private String name;

    CourseSubject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<CourseSubject> getCourseByName(String name) {
        return Arrays.stream(CourseSubject.values())
                .filter(CourseSubject -> CourseSubject.getName().equals(name))
                .findAny();
    }
}
