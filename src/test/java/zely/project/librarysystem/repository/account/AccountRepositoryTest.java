package zely.project.librarysystem.repository.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import zely.project.librarysystem.bootstrap.BootstrapData;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.service.account.AccountCsvServiceImpl;
import zely.project.librarysystem.service.library.LibraryCsvServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({BootstrapData.class, AccountCsvServiceImpl.class, LibraryCsvServiceImpl.class})
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void findAccountByPersonNameContains() {
        List<Account> list = accountRepository.findAccountByPersonNameIsLikeIgnoreCase("%Charlie%");

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void findAccountByPersonNameIsLikeIgnoreCaseAndAccountType() {

        List<Account> list = accountRepository.findAccountByPersonNameIsLikeIgnoreCaseAndAccountType("%Charlie%", AccountType.MEMBER);

        assertThat(list.size()).isEqualTo(1);

    }

    @Test
    void findAllByAccountType() {
        List<Account> list = accountRepository.findAllByAccountType(AccountType.LIBRARIAN);

        assertThat(list.size()).isEqualTo(4);
    }


}