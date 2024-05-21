package zely.project.librarysystem.service.book;


import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.book.BookItemSummaryDto;

import java.util.List;
import java.util.Optional;

@Service
public interface BookItemService {

    List<BookItemSummaryDto> getAllBookItems();

    Optional<BookItemSummaryDto> getBookItemById(Integer id);

    Optional<BookItemSummaryDto> getBookItemByBookItemByBarCode(String bookBarcode);

    BookItemDto createNewBookItem(BookItemDto bookItemDto);

    Optional<BookItemDto> updateBookItemById (Integer id, BookItemDto bookItemDto);

    BookItemSummaryDto updateBookItemStatus(BookItemSummaryDto bookItemSummaryDto, BookStatus status);

    boolean deleteBookItemById(Integer id);

}



