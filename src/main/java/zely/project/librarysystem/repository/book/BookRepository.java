package zely.project.librarysystem.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.book.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer>{

    Optional<Book> getBookByIsbn(String isbn);

    Optional<Book> findBookBySubject(String subject);
}
