package zely.project.librarysystem.service.account;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.repository.account.AccountRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AuthorizationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountByPerson_Email(username);
    }
}
