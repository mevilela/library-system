package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.book.BookDto;
import zely.project.librarysystem.dto.book.BookResponseDto;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    List<BookResponseDto> getAllBooks();

    Optional<BookResponseDto> getBookById(Integer id);

    BookResponseDto createNewBook(BookResponseDto bookDto);

    Optional<BookResponseDto> updateBookById(Integer id, BookResponseDto bookDto);

    boolean deleteBookById(Integer id);

    Optional<BookResponseDto> getBookByIsbn(String isbn);
}
