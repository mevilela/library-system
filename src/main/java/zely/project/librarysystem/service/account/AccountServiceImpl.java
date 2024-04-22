package zely.project.librarysystem.service.account;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.repository.account.AccountRepository;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public List<Account> getAllMembers() {
        return null;
    }

    @Override
    public List<Account> getAllLibrarians() {
        return null;
    }
}
