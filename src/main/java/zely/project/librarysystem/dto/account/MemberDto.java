package zely.project.librarysystem.dto.account;

import lombok.Getter;
import lombok.Setter;
import zely.project.librarysystem.domain.account.AccountType;

import java.time.LocalDate;
@Getter
@Setter
public class MemberDto extends AccountDto {

    private LocalDate dateOfMembership;
    private Integer totalBooksCheckedOut;

    public MemberDto() {
        this.setAccountType(AccountType.MEMBER); // Define o accountType como LIBRARIAN
    }

    public MemberDto(LocalDate dateOfMembership, Integer totalBooksCheckedOut) {
        this.dateOfMembership = dateOfMembership;
        this.totalBooksCheckedOut = totalBooksCheckedOut;
        this.setAccountType(AccountType.LIBRARIAN); // Define o accountType como LIBRARIAN
    }
}
