package zely.project.librarysystem.domain.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class Librarian extends Account {
    private String department;


    public Librarian() {
    }

    public Librarian(String password, AccountType accountType, AccountStatus accountStatus, Person person, String department) {
        super(password, accountType, accountStatus, person);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
