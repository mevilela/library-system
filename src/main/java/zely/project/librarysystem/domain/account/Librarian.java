package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
public class Librarian extends Account {

    private java.lang.String department;


    public Librarian() {
        super();
        this.setAccountType(AccountType.LIBRARIAN);
    }


    public Librarian(String department) {
        super();
        this.department = department;
        this.setAccountType(AccountType.LIBRARIAN);
    }

}
