package zely.project.librarysystem.service.book;


import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.dto.book.BookItemDto;

import java.util.List;
import java.util.Optional;

@Service
public interface BookItemService {

    List<BookItemDto> getAllBookItems();

    Optional<BookItemDto> getBookItemById(Integer id);

    Optional<BookItemDto> getBookItemByBookItemBarCode(String bookBarcode);

    BookItemDto createNewBookItem(BookItemDto bookItemDto);

    Optional<BookItemDto> updateBookItemById (Integer id, BookItemDto bookItemDto);

    boolean deleteBookItemById(Integer id);

}



