package zely.project.librarysystem.service.account;


import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.account.Account;

import java.util.List;

@Service
public interface AccountService {

    List<Account> getAllAccounts();

    List<Account> getAllMembers();

    List<Account> getAllLibrarians();
}
