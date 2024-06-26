package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.dto.book.BookDto;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.book.BookItemSummaryDto;
import zely.project.librarysystem.dto.library.RackDto;
import zely.project.librarysystem.mapper.BookItemMapper;
import zely.project.librarysystem.repository.book.BookItemRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookItemServiceImpl implements BookItemService {

    private final BookItemRepository bookItemRepository;
    private final BookItemMapper bookItemMapper;

    public BookItemServiceImpl(BookItemRepository bookItemRepository, BookItemMapper bookItemMapper) {
        this.bookItemRepository = bookItemRepository;
        this.bookItemMapper = bookItemMapper;
    }

    @Override
    public List<BookItemSummaryDto> getAllBookItems() {

       List<BookItem> bookItems = bookItemRepository.findAll();

       return bookItems.stream().map(
               bookItemMapper::toBookItemSummaryDto
       ).collect(Collectors.toList());

    }

    @Override
    public Optional<BookItemSummaryDto> getBookItemByPublicationDate(LocalDate date) {
        return Optional.ofNullable(bookItemMapper.toBookItemSummaryDto((bookItemRepository.getBookItemByPublicationDate(date)).orElse(null)));
    }

    @Override
    public Optional<BookItemSummaryDto> getBookItemById(Integer id) {
        return Optional.ofNullable(bookItemMapper.toBookItemSummaryDto((bookItemRepository.findById(id)).orElse(null)));

    }

    @Override
    public Optional<BookItemSummaryDto> getBookItemByBookItemByBarCode(String bookBarcode) {
        return Optional.ofNullable(bookItemMapper.toBookItemSummaryDto((bookItemRepository.getBookItemByBookBarcode(bookBarcode)).orElse(null)));

    }

    @Override
    public BookItemDto createNewBookItem(BookItemDto bookItemDto) {
        return bookItemMapper.toBookItemDto(bookItemRepository.save(bookItemMapper.toBookItemEntity(bookItemDto)));

    }

    @Override
    public Optional<BookItemDto> updateBookItemById(Integer id, BookItemDto bookItemDto) {
        return bookItemRepository.findById(id).map(
                foundBookItem -> {
                    foundBookItem.setStatus(bookItemDto.getBookStatus());
                    foundBookItem.setBookBarcode(bookItemDto.getBookBarcode());
                    foundBookItem.setPrice(bookItemDto.getPrice());
                    foundBookItem.setDueDate(bookItemDto.getDueDate());
                    foundBookItem.setBorrowDate(bookItemDto.getBorrowDate());
                    foundBookItem.setDateOfPurchase(bookItemDto.getDateOfPurchase());
                    foundBookItem.setPublicationDate(bookItemDto.getPublicationDate());
                    foundBookItem.setStatus(bookItemDto.getBookStatus());

                    if (bookItemDto.getBook() != null){
                        Book book = getBook(bookItemDto);
                        foundBookItem.setBook(book);
                    } else {
                        throw new NullPointerException("Book cannot be null");
                    }

                    if (bookItemDto.getRack() != null){
                        Rack rack = getRack(bookItemDto);
                        foundBookItem.setRack(rack);
                    } else {
                        throw new NullPointerException("Rack cannot be null");
                    }

                    return foundBookItem;

                }).map(bookItemRepository::save).map(bookItemMapper::toBookItemDto);
    }

    @Override
    public BookItemSummaryDto updateBookItemStatus(BookItemSummaryDto bookItemSummaryDto, BookStatus bookStatus) {

        bookItemSummaryDto.setBookStatus(bookStatus);

        bookItemRepository.save(bookItemMapper.toBookItemFromSummaryDto(bookItemSummaryDto));

        return bookItemSummaryDto;
    }

    private static Rack getRack(BookItemDto bookItemDto){
        RackDto rackDto = bookItemDto.getRack();

        Rack rack = new Rack();
        rack.setId(bookItemDto.getRack().getId());

        return rack;
    }

    private static Book getBook(BookItemDto bookItemDto){
        BookDto bookDto = bookItemDto.getBook();

        Book book = new Book();
        book.setId(bookItemDto.getId());

        return book;
    }



    @Override
    public boolean deleteBookItemById(Integer id) {

        if(bookItemRepository.existsById(id)){
            bookItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
