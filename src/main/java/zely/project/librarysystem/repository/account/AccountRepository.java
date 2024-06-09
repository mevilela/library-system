package zely.project.librarysystem.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountType;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAccountByPersonNameIsLikeIgnoreCase(String name);

    List<Account> findAccountByPersonNameIsLikeIgnoreCaseAndAccountType(String name, AccountType type);

    List<Account> findAllByAccountType(AccountType type);

    UserDetails findAccountByPerson_Email(String email);


}
