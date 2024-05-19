package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.book.BookDto;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    List<BookDto> getAllBooks();

    Optional<BookDto> getBookById(Integer id);

    BookDto createNewBook(BookDto bookDto);

    Optional<BookDto> updateBookById(Integer id, BookDto bookDto);

    boolean deleteBookById(Integer id);
}
