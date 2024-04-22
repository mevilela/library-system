package zely.project.librarysystem.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zely.project.librarysystem.domain.account.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}