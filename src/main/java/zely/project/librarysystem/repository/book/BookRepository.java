package zely.project.librarysystem.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.book.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
}
