package zely.project.librarysystem.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.book.BookItem;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookItemRepository extends JpaRepository<BookItem, Integer> {

    Optional<BookItem> getBookItemByBookBarcode(String bookBarCode);

    Optional<BookItem> getBookItemByPublicationDate(LocalDate date);

}
