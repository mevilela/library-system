package zely.project.librarysystem.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.domain.account.AccountStatus;
import zely.project.librarysystem.domain.account.AccountType;



    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class AccountCreateRequestDto {

        private AccountType accountType;

        private String password;

        private PersonDto person;


}
