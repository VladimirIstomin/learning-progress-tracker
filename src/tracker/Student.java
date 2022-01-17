package tracker;

public class Student {
    private static final String namePattern = "[a-zA-Z][ a-zA-Z'-]*[a-zA-Z]";
    private static final String adjacentSpecialSymbolsNamePattern = ".*(-'|'-|--|'').*";
    private static final String emailPattern = "[\\w.]+@[\\w.]+\\.[\\w.]+";

    private String firstName;
    private String lastName;
    private String email;

    private int javaPoints;
    private int dsaPoints;
    private int databasesPoints;

    private int springPoints;

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

   public void addPoints(int javaPoints, int dsaPoints, int databasesPoints, int springPoints) {
       if (javaPoints < 0 || dsaPoints < 0 || databasesPoints < 0 || springPoints < 0) {
           throw new RuntimeException("Incorrect points format.");
       }
       this.javaPoints += javaPoints;
       this.dsaPoints += dsaPoints;
       this.databasesPoints += databasesPoints;
       this.springPoints += springPoints;
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

    public int getJavaPoints() {
        return javaPoints;
    }

    public int getDsaPoints() {
        return dsaPoints;
    }

    public int getDatabasesPoints() {
        return databasesPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }
}
