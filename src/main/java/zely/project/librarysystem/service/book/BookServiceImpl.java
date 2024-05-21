package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.dto.book.*;
import zely.project.librarysystem.mapper.BookMapper;
import zely.project.librarysystem.repository.book.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }


    @Override
    public List<BookDto> getAllBooks() {

        List<Book> bookList = bookRepository.findAll();
        return bookMapper.toBookDtoList(bookList);

    }

    @Override
    public Optional<BookDto> getBookById(Integer id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookDto);

    }

    @Override
    public BookCreateDto createNewBook(BookCreateDto bookCreateDto) {

        return bookMapper.toCreateFromBook(bookRepository.save(bookMapper.toBookFromCreate(bookCreateDto)));

    }


    @Override
    public Optional<BookDto> updateBookById(Integer id, BookDto bookDto) {
        return bookRepository.findById(id).map(
                foundBook -> {
                    foundBook.setIsbn(bookDto.getIsbn());
                    foundBook.setTitle(bookDto.getTitle());
                    foundBook.setLanguage(bookDto.getLanguage());
                    foundBook.setSubject(bookDto.getSubject());
                    foundBook.setNumberOfPages(bookDto.getNumberOfPages());

                    return foundBook;
                }).map(bookRepository::save).map(bookMapper::toBookDto);
    }



    @Override
    public boolean deleteBookById(Integer id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<BookDto> getBookByIsbn(String isbn) {
        return Optional.ofNullable(bookMapper.toBookDto((bookRepository.getBookByIsbn(isbn)).orElse(null)));

    }


}

