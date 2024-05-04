package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;

@Entity
public class Librarian extends Account {
    private java.lang.String department;


    public Librarian() {
    }

    public Librarian(Integer id, String password, AccountType accountType, AccountStatus accountStatus, Person person, String department) {
        super(id, password, accountType, accountStatus, person);
        this.department = department;
    }

    public java.lang.String getDepartment() {
        return department;
    }

    public void setDepartment(java.lang.String department) {
        this.department = department;
    }
}
