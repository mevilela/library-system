package zely.project.librarysystem.bootstrap;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.webjars.NotFoundException;
import zely.project.librarysystem.csv.*;
import zely.project.librarysystem.domain.account.*;
import zely.project.librarysystem.domain.book.*;
import zely.project.librarysystem.domain.card.Card;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.repository.account.AccountRepository;
import zely.project.librarysystem.repository.book.AuthorRepository;
import zely.project.librarysystem.repository.book.BookItemRepository;
import zely.project.librarysystem.repository.book.BookRepository;
import zely.project.librarysystem.repository.book.PublisherRepository;
import zely.project.librarysystem.repository.card.CardRepository;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.repository.library.RackRepository;
import zely.project.librarysystem.service.account.csv.AccountCsvService;
import zely.project.librarysystem.service.book.csv.AuthorCsvService;
import zely.project.librarysystem.service.book.csv.BookCsvService;
import zely.project.librarysystem.service.book.csv.BookItemCsvService;
import zely.project.librarysystem.service.book.csv.PublisherCsvService;
import zely.project.librarysystem.service.card.CardCsvService;
import zely.project.librarysystem.service.library.LibraryCsvService;
import zely.project.librarysystem.service.library.RackCsvService;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static zely.project.librarysystem.domain.account.AccountType.LIBRARIAN;
import static zely.project.librarysystem.domain.account.AccountType.MEMBER;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {


    private final LibraryRepository libraryRepository;
    private final LibraryCsvService libraryCsvService;
    private final AccountRepository accountRepository;
    private final AccountCsvService accountCsvService;
    private final RackRepository rackRepository;
    private final RackCsvService rackCsvService;
    private final CardRepository cardRepository;
    private final CardCsvService cardCsvService;
//    private final LibraryCodeRepository libraryCodeRepository;
    private final PublisherRepository publisherRepository;
    private final PublisherCsvService publisherCsvService;
    private final AuthorRepository authorRepository;
    private final AuthorCsvService authorCsvService;
    private final BookRepository bookRepository;
    private final BookCsvService bookCsvService;
    private final BookItemRepository bookItemRepository;
    private final BookItemCsvService bookItemCsvService;


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadLibraryData();
        loadLibraryCsvData();
        loadAccountData();
        loadAccountCsvData();
        loadRackCsvData();
        loadCardCsvData();
        loadPublisherCsvData();
        loadAuthorCsvData();
        loadBookCsvData();
        loadBookItemCsvData();
    }

    private void loadBookItemCsvData() throws FileNotFoundException {
        if (bookItemRepository.count() < 1) {
            File file = ResourceUtils.getFile("classpath:csvdata/bookItem-data.csv");
            List<BookItemCsvRecord> recs = bookItemCsvService.convertCSV(file);


            for (BookItemCsvRecord record : recs){

                Book book = new Book();
                book.setId(record.getBookId());

                Rack rack = new Rack();
                rack.setId(record.getRackId());

                BookItem bookItem = new BookItem();
                bookItem.setBook(book);
                bookItem.setRack(rack);
                bookItem.setBookBarcode(record.getBookBarcode());
                bookItem.setFormat(Format.valueOf(record.getFormat().toUpperCase()));
                bookItem.setStatus(BookStatus.valueOf(record.getBookStatus().toUpperCase()));
                bookItem.setBorrowDate(record.getBorrowDate());
                bookItem.setDueDate(record.getDueDate());
                bookItem.setDateOfPurchase(record.getDateOfPurchase());
                bookItem.setPrice(record.getPrice());
                bookItem.setPublicationDate(record.getPublicationDate());
                bookItemRepository.save(bookItem);
            }
        }
    }

    private void loadBookCsvData() throws FileNotFoundException {
        if (bookRepository.count() < 1) {
            File file = ResourceUtils.getFile("classpath:csvdata/book-data.csv");
            List<BookCsvRecord> recs = bookCsvService.convertCSV(file);

            for (BookCsvRecord record : recs){
                Book book = new Book();
                book.setIsbn(record.getIsbn());
                book.setTitle(record.getTitle());
                book.setSubject(record.getSubject());
                book.setLanguage(record.getLanguage());
                book.setNumberOfPages(record.getNumberOfPages());

                List<String> authorIds = Arrays.stream(record.getAuthorIds().split(",")).toList();

                Set<Author> authorList = new HashSet<>();
                for(String authorId : authorIds){
                    authorList.add(authorRepository.findById(Integer.parseInt(authorId)).orElseThrow());
                }

                book.setAuthors(authorList);

                Publisher publisher = new Publisher();
                publisher.setId(record.getPublisherId());

                book.setPublisher(publisher);

                bookRepository.save(book);
            }
        }
    }

    private void loadAuthorCsvData() throws FileNotFoundException {
        if (authorRepository.count() < 1) {
            File file = ResourceUtils.getFile("classpath:csvdata/author-data.csv");
            List<AuthorCsvRecord> recs = authorCsvService.convertCSV(file);

            for (AuthorCsvRecord record : recs) {
                Author author = new Author();
                author.setAuthorName(record.getAuthorName());
                authorRepository.save(author);
            }
        }
    }

    private void loadPublisherCsvData() throws FileNotFoundException {

        if (publisherRepository.count() < 1) {
            File file = ResourceUtils.getFile("classpath:csvdata/publisher-data.csv");
            List<PublisherCsvRecord> recs = publisherCsvService.convertCSV(file);

            for (PublisherCsvRecord record : recs){
                Publisher publisher = new Publisher();
                publisher.setPublisherName(record.getPublisherName());
                publisherRepository.save(publisher);
            }

        }
    }


    private void loadLibraryCsvData() throws FileNotFoundException {
        if (libraryRepository.count() < 1) {
            File file = ResourceUtils.getFile("classpath:csvdata/library-data.csv");
            List<LibraryCSVRecord> recs = libraryCsvService.convertCSV(file);

            for (LibraryCSVRecord record : recs){
                Library library = new Library();
                library.setId(record.getId());
                library.setName(record.getName());
                library.setAddress(record.getAddress());

                libraryRepository.save(library);

            }
        }
    }

    private void loadAccountCsvData() throws FileNotFoundException{
        if (accountRepository.count() < 1){
            File file = ResourceUtils.getFile("classpath:csvdata/account-data.csv");
            List<AccountCsvRecord> accountRecs = accountCsvService.convertCSV(file);

            for(AccountCsvRecord accountRecord : accountRecs){

                String accountTypeStr = String.valueOf(accountRecord.getAccountType().toUpperCase());

                AccountType accountType;
                Account account;

                try {
                    accountType = AccountType.valueOf(accountTypeStr);
                } catch (IllegalArgumentException ex) {
                    throw new IllegalArgumentException("Unknown account type: " + accountTypeStr);
                }

                switch (accountType) {
                    case MEMBER:
                        account = new Member();
                        ((Member) account).setAccountType(MEMBER);
                        ((Member) account).setDateOfMembership(accountRecord.getDateOfMembership());
                        ((Member) account).setTotalBooksCheckedOut(accountRecord.getTotalBooksCheckedOut());
                        break;

                    case LIBRARIAN:
                        account = new Librarian();
                        ((Librarian) account).setAccountType(LIBRARIAN);
                        ((Librarian) account).setDepartment(accountRecord.getDepartment());
                        break;

                    default:
                        throw new IllegalArgumentException("Unsupported account type: " + accountType);
                }


//                if (account.getAccountType().equals("MEMBER")) {
//                    account = new Member();
//                    account.setAccountType(MEMBER);
//                    ((Member) account).setDateOfMembership(accountRecord.getDateOfMembership());
//                    ((Member) account).setTotalBooksCheckedOut(accountRecord.getTotalBooksCheckedOut());
//                } else if (accountType.equals(LIBRARIAN)) {
//                    account = new Librarian();
//                    account.setAccountType(LIBRARIAN);
//                    ((Librarian) account).setDepartment(accountRecord.getDepartment());
//                } else {
//                    throw new IllegalArgumentException("Unknown account type");
//                }

                Person person = new Person(accountRecord.getName(), accountRecord.getAddress(), accountRecord.getEmail(), accountRecord.getPhone());
                account.setPerson(person);
                account.setPassword(accountRecord.getPassword());
                account.setAccountStatus(AccountStatus.valueOf(accountRecord.getAccountStatus().toUpperCase()));

                accountRepository.save(account);

            }
        }
    }


    private void loadAccountData(){

        if (libraryRepository.count() < 1){

            Person maria = new Person("Maria", "Rua Paissandu 344", "maria@gmail.com", "99199-2032");

            Member member = new Member();
            member.setPerson(maria);
            member.setId(88);
            member.setAccountType(MEMBER);
            member.setAccountStatus(AccountStatus.ACTIVE);
            member.setPassword("123");
            member.setDateOfMembership(LocalDate.of(2024, 04, 12));
            member.setTotalBooksCheckedOut(4);

            Person joao = new Person("Joao", "Rua Paissandu 344", "joao@gmail.com", "99199-0000");
            Librarian librarian = new Librarian();
            librarian.setId(99);
            librarian.setPerson(joao);
            librarian.setDepartment("science");
            librarian.setAccountStatus(AccountStatus.ACTIVE);
            librarian.setAccountType(MEMBER);
            librarian.setPassword("123");

            accountRepository.save(member);
            accountRepository.save(librarian);

        }
    }

    private void loadLibraryData(){


        if (libraryRepository.count() < 0){
            Library library1 = new Library();
            Card card = new Card();
            library1.setAddress("rua das flores, 10");
            library1.setName("Library 1");
            card.setLibrary(library1);
            card.setBarcode("45454867654116");
            libraryRepository.save(library1);
            cardRepository.save(card);

        }
    }

    private void loadRackCsvData() throws FileNotFoundException{

        if (rackRepository.count() == 0){
            File file = ResourceUtils.getFile("classpath:csvdata/rack-data.csv");
            List<RackCsvRecord> rackRecords = rackCsvService.convertCSV(file);

            for (RackCsvRecord record : rackRecords){
                Rack rack = new Rack();

                rack.setRackNumber(record.getRackNumber());
                rack.setLocation(record.getLocation());
                rack.setSection(record.getSection());
                rack.setLibrary(libraryRepository.findById(record.getLibraryId()).orElseThrow());

                rackRepository.save(rack);
            }

        }
    }

    private void loadCardCsvData() throws FileNotFoundException {
        if (cardRepository.count() < 10){

            File file = ResourceUtils.getFile("classpath:csvdata/card-data.csv");
            List<CardCsvRecord> cardCsvRecords = cardCsvService.convertCSV(file);

            for (CardCsvRecord cardRecords : cardCsvRecords){
                Card card = new Card();

                card.setBarcode(cardRecords.getBarcode());
                card.setActive(cardRecords.isActive());
                card.setIssuedAt(cardRecords.getIssuedAt());
                card.setLibrary(libraryRepository.findById(cardRecords.getLibraryId()).orElseThrow());

                Account account = accountRepository.findById(cardRecords.getAccountId())
                        .orElseThrow(() -> new NotFoundException("Account not found"));

                card.setAccount(account);

                cardRepository.save(card);

            }

        }

    }

}
