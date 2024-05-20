package zely.project.librarysystem.service.book;


import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.book.BookItemDto;

import java.util.List;
import java.util.Optional;

@Service
public interface BookItemService {

    List<BookItemDto> getAllBookItems();

    Optional<BookItemDto> getBookItemById(Integer id);

    Optional<BookItemDto> getBookItemByBookItemByBarCode(String bookBarcode);

    BookItemDto createNewBookItem(BookItemDto bookItemDto);

    Optional<BookItemDto> updateBookItemById (Integer id, BookItemDto bookItemDto);

    boolean deleteBookItemById(Integer id);

}



