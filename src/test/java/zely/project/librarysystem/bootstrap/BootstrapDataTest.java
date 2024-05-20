package zely.project.librarysystem.bootstrap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import zely.project.librarysystem.repository.account.AccountRepository;
import zely.project.librarysystem.repository.book.AuthorRepository;
import zely.project.librarysystem.repository.book.BookItemRepository;
import zely.project.librarysystem.repository.book.BookRepository;
import zely.project.librarysystem.repository.book.PublisherRepository;
import zely.project.librarysystem.repository.card.CardRepository;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.repository.library.RackRepository;
import zely.project.librarysystem.service.account.csv.AccountCsvService;
import zely.project.librarysystem.service.account.csv.AccountCsvServiceImpl;
import zely.project.librarysystem.service.book.csv.*;
import zely.project.librarysystem.service.card.CardCsvService;
import zely.project.librarysystem.service.card.CardCsvServiceImpl;
import zely.project.librarysystem.service.library.LibraryCsvService;
import zely.project.librarysystem.service.library.LibraryCsvServiceImpl;
import zely.project.librarysystem.service.library.RackCsvService;
import zely.project.librarysystem.service.library.RackCsvServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({LibraryCsvServiceImpl.class, AccountCsvServiceImpl.class, RackCsvServiceImpl.class, CardCsvServiceImpl.class,
        PublisherCsvServiceImpl.class, AuthorCsvServiceImpl.class, BookCsvServiceImpl.class, BookItemCsvServiceImpl.class})
public class BootstrapDataTest {
    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    LibraryCsvService libraryCsvService;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountCsvService accountCsvService;
    @Autowired
    RackRepository rackRepository;

    @Autowired
    RackCsvService rackCsvService;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardCsvService cardCsvService;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    PublisherCsvService publisherCsvService;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorCsvService authorCsvService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCsvService bookCsvService;

    @Autowired
    BookItemRepository bookItemRepository;

    @Autowired
    BookItemCsvService bookItemCsvService;

    BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {

        bootstrapData = new BootstrapData(libraryRepository, libraryCsvService, accountRepository,
                accountCsvService, rackRepository, rackCsvService, cardRepository, cardCsvService, publisherRepository,
                publisherCsvService, authorRepository, authorCsvService, bookRepository, bookCsvService, bookItemRepository, bookItemCsvService);
    }

    @Test
    void TestRun() throws Exception {
        bootstrapData.run(null);

        assertThat(libraryRepository.count()).isEqualTo(10);
        assertThat(accountRepository.count()).isEqualTo(10);
        assertThat(rackRepository.count()).isEqualTo(10);
        assertThat(cardRepository.count()).isEqualTo(10);
        assertThat(publisherRepository.count()).isEqualTo(10);
        assertThat(authorRepository.count()).isEqualTo(10);
        assertThat(bookRepository.count()).isEqualTo(20);
        assertThat(bookItemRepository.count()).isEqualTo(40);
    }
}











