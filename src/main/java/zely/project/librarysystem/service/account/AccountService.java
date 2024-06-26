package zely.project.librarysystem.service.account;


import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.dto.account.AccountCreateRequestDto;
import zely.project.librarysystem.dto.account.AccountDto;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {

    List<AccountDto> getAllAccounts(String name, AccountType type);

    List<Account> getAllMembers();

    List<Account> getAllLibrarians();

    Optional<AccountDto> getAccountById(Integer id);

    AccountCreateRequestDto createNewAccount(AccountCreateRequestDto accountCreateRequestDto);

    Optional<AccountDto> updateAccountById(Integer id, AccountDto accountDto);

    boolean deleteAccountById(Integer id);


}
