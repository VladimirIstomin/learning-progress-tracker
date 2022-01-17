package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student student;

    @BeforeEach
    void setUpStudent() {
        student = new Student();
    }

    @ParameterizedTest(name = "[{index}] {0} - incorrect first name")
    @MethodSource("incorrectNameFactory")
    void setFirstNameIncorrect(String incorrectFirstName) {
        assertThrows(RuntimeException.class, () -> student.setFirstName(incorrectFirstName));
    }

    @ParameterizedTest(name = "[{index}] {0} - incorrect last name")
    @MethodSource("incorrectNameFactory")
    void setLastNameIncorrect(String incorrectLastName) {
        assertThrows(RuntimeException.class, () -> student.setLastName(incorrectLastName));
    }

    static List<String> incorrectNameFactory() {
        return List.of(
                "", "    ", // empty name
                "h", "H", // too short name
                "hello/", "helloё", "hello12", // name contains extra characters
                "hello''hello", "hello'-hello", "hello-'hello", "hello--hello", // name has adjacent special characters
                "hello'", "hello-", "'hello", "-hello"); // name has special characters at the start or at the end of a name
    }

    @ParameterizedTest(name = "[{index}] {0} - correct first name")
    @MethodSource("correctNameFactory")
    void setFirstNameCorrect(String firstNameCorrect) {
        student.setFirstName(firstNameCorrect);
        assertEquals(firstNameCorrect, student.getFirstName());
    }

    @ParameterizedTest(name = "[{index}] {0} - correct last name")
    @MethodSource("correctNameFactory")
    void setLastNameCorrect(String lastNameCorrect) {
        student.setLastName(lastNameCorrect);
        assertEquals(lastNameCorrect, student.getLastName());
    }

    static List<String> correctNameFactory() {
        return List.of(
                "hello", "hell'o", "hel-lo",
                "he'l'o", "he-l-l'o", "HeLlO", "hello world");
    }

    @ParameterizedTest(name = "[{index}] {0} - incorrect email")
    @MethodSource("incorrectEmailFactory")
    void setEmailIncorrect(String emailIncorrect) {
        assertThrows(RuntimeException.class, () -> student.setEmail(emailIncorrect));
    }

    static List<String> incorrectEmailFactory() {
        return List.of(
                "", "   ", // empty email
                "hello@world@com", "hello@world@hello.com", // several 'at' signs
                "@hello.com", "@hello", "  @hello.com", // no name part
                "hello", "hello.com", // no domain part
                "hello@world" // incorrect domain
        );
    }

    @ParameterizedTest(name = "[{index}] {0} - correct email")
    @MethodSource("correctEmailFactory")
    void setEmailCorrect(String emailCorrect) {
        student.setEmail(emailCorrect);
        assertEquals(emailCorrect, student.getEmail());
    }

    static List<String> correctEmailFactory() {
        return List.of(
                "hello@world.com", "hello.hello@wordl.com", "hello@world.world.com",
                "123@123.123", "hello123@hello.123.com"
        );
    }

    @Test
    void addPointsIncorrect() { // points cannot be negative
        assertThrows(RuntimeException.class, () -> student.addPoints(-1, 1, 1, 1));
    }

    @Test
    void addPointsCorrect() {
        student.addPoints(1, 2, 3, 4);
        assertEquals(1, student.getJavaPoints());
        assertEquals(2, student.getDsaPoints());
        assertEquals(3, student.getDatabasesPoints());
        assertEquals(4, student.getSpringPoints());

        student.addPoints(4, 3, 2, 1);
        assertEquals(5, student.getJavaPoints());
        assertEquals(5, student.getDsaPoints());
        assertEquals(5, student.getDatabasesPoints());
        assertEquals(5, student.getSpringPoints());
    }
}
