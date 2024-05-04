package zely.project.librarysystem.controller.bootstrap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import zely.project.librarysystem.bootstrap.BootstrapData;
import zely.project.librarysystem.repository.account.AccountRepository;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.service.account.AccountCsvService;
import zely.project.librarysystem.service.account.AccountCsvServiceImpl;
import zely.project.librarysystem.service.library.LibraryCsvService;
import zely.project.librarysystem.service.library.LibraryCsvServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({LibraryCsvServiceImpl.class, AccountCsvServiceImpl.class})
public class BootstrapDataTest {
    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LibraryCsvService libraryCsvService;

    @Autowired
    AccountCsvService accountCsvService;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(libraryRepository, accountRepository, libraryCsvService, accountCsvService);
    }

    @Test
    void Testrun() throws Exception {
        bootstrapData.run(null);

        assertThat(libraryRepository.count()).isEqualTo(136);
        assertThat(accountRepository.count()).isEqualTo(12);
    }
}











