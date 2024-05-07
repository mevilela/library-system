package zely.project.librarysystem.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.book.BookItem;

public interface BookItemRepository extends JpaRepository<BookItem, Integer> {
}
