package zely.project.librarysystem.repository.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zely.project.librarysystem.domain.book.*;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.repository.library.RackRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookItemRepositoryTest {
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

    @Transactional
    @Test
    void testGivingABookToBookItem() {
        Set<Book> books = new HashSet<>();
        Set<Author> authors = new HashSet<>();

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

        assertNotNull(beHappy.getId(), "O livro deveria ter um ID após ser salvo.");

        assertTrue(johnAdams.getBooks().size() > 0, "O autor deveria ter pelo menos um livro.");

        assertThat(bookItem.getBook().getTitle()).isEqualTo(beHappy.getTitle());
    }
}