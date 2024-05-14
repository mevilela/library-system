package zely.project.librarysystem.repository.booking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.domain.book.*;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.domain.card.Card;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.repository.account.AccountRepository;
import zely.project.librarysystem.repository.book.AuthorRepository;
import zely.project.librarysystem.repository.book.BookItemRepository;
import zely.project.librarysystem.repository.book.BookRepository;
import zely.project.librarysystem.repository.book.PublisherRepository;
import zely.project.librarysystem.repository.card.CardRepository;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.repository.library.RackRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    BookItemRepository bookItemRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    RackRepository rackRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    @Test
    void testSaveReservation() {
        Set<Book> books = new HashSet<>();
        Set<Author> authors = new HashSet<>();
        Set<Card> cards = new HashSet<>();
        Set<Reservation> reservations = new HashSet<>();

        Account member = accountRepository.findAllByAccountType(AccountType.MEMBER).get(1);

        Card card = Card.builder()
                .account(member)
                .reservations(reservations)
                .active(true)
                .barcode("123456789")
                .lendings(null)
                .issuedAt(LocalDate.of(2023,10,10))
                .library(libraryRepository.findById(1).get())
                .build();

        cardRepository.save(card);

        Author johnAdams = Author.builder()
                .books(books)
                .authorName("John Adams")
                .build();

        Publisher century = Publisher.builder()
                .publisherName("Century")
                .build();

        authors.add(johnAdams);

        Book beHappy = Book.builder()
                .authors(authors)
                .isbn("45SFGH5555")
                .title("Be Happy")
                .subject("Self-care")
                .language("English")
                .numberOfPages(156)
                .publisher(century)
                .bookItemSet(new HashSet<>())
                .build();

        johnAdams.getBooks().add(beHappy);

        // Salva todos os objetos no banco de dados
        authorRepository.save(johnAdams);
        publisherRepository.save(century);
        bookRepository.save(beHappy);

        //creating rack
        Rack rack = Rack.builder()
                .rackNumber(12)
                .section("zone A")
                .location("first floor")
                .build();

        rackRepository.save(rack);

        //creating bookItem
        BookItem bookItem = BookItem.builder()
                .bookBarcode("7854333311")
                .book(beHappy)
                .borrowDate(LocalDate.of(2024, 03,31))
                .dateOfPurchase(LocalDate.of(2022, 02, 14))
                .price(2.00)
                .status(BookStatus.AVAILABLE)
                .dueDate(LocalDate.now())
                .format(Format.PAPERBACK)
                .rack(rack)
                .publicationDate(LocalDate.of(2022, 01, 02))
                .build();

        bookItemRepository.save(bookItem);


        Reservation reservation = Reservation.builder()
                .creationDate(LocalDate.of(2024,11,05))
                .bookItem(bookItem)
                .card(card)
                .active(true)
                .build();

        reservationRepository.save(reservation);

        assertNotNull(reservation.getId());
    }
}
