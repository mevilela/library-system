package zely.project.librarysystem.dto.account;


import zely.project.librarysystem.domain.account.AccountStatus;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.domain.account.Person;

public class AccountDto {

    private Integer id;

    private AccountType accountType;

    private AccountStatus accountStatus;

    private PersonDto person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }
}
