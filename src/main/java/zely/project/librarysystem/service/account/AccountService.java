package zely.project.librarysystem.service.account;


import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.dto.account.AccountDto;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {

    List<AccountDto> getAllAccounts(java.lang.String name);

    List<Account> getAllMembers();

    List<Account> getAllLibrarians();

    Optional<AccountDto> getAccountById(Integer id);

    AccountDto createNewAccount(AccountDto accountDto);

    Optional<AccountDto> updateAccountById(Integer id, AccountDto accountDto);

    boolean deleteAccountById(Integer id);
}
