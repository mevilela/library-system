package zely.project.librarysystem.dto.account;

import lombok.Getter;
import lombok.Setter;
import zely.project.librarysystem.domain.account.AccountType;

@Getter
@Setter
public class LibrarianDto extends AccountDto {

    private String department;


    public LibrarianDto() {
        this.setAccountType(AccountType.LIBRARIAN); // Define o accountType como LIBRARIAN
    }

    public LibrarianDto(String department) {
        this.department = department;
        this.setAccountType(AccountType.LIBRARIAN); // Define o accountType como LIBRARIAN
    }
}
