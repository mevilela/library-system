package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import zely.project.librarysystem.domain.card.LibraryCard;

import java.util.Set;

@Entity
public class Librarian extends Account {
    private java.lang.String department;


    public Librarian() {
    }

    public Librarian(Integer id, String password, AccountType accountType, AccountStatus accountStatus, Person person, Set<LibraryCard> libraryCards, String department) {
        super(id, password, accountType, accountStatus, person, libraryCards);
        this.department = department;
    }

    public java.lang.String getDepartment() {
        return department;
    }

    public void setDepartment(java.lang.String department) {
        this.department = department;
    }
}
