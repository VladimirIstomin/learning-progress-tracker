package tracker;

public class Student {
    private static final String namePattern = "[a-zA-Z][ a-zA-Z'-]*[a-zA-Z]";
    private static final String adjacentSpecialSymbolsNamePattern = ".*(-'|'-|--|'').*";
    private static final String emailPattern = "[\\w.]+@[\\w.]+\\.[\\w.]+";

    private String firstName;
    private String lastName;
    private String email;

    public void setFirstName(String firstName) {
        if (firstName.matches(namePattern) && !firstName.matches(adjacentSpecialSymbolsNamePattern)) {
            this.firstName = firstName;
        } else {
            throw new RuntimeException("Incorrect first name.");
        }
    }

    public void setLastName(String lastName) {
        if (lastName.matches(namePattern) && !lastName.matches(adjacentSpecialSymbolsNamePattern)) {
            this.lastName = lastName;
        } else {
            throw new RuntimeException("Incorrect last name.");
        }
    }

    public void setEmail(String email) {
        if (email.matches(emailPattern)) {
            this.email = email;
        } else {
            throw new RuntimeException("Incorrect email.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
