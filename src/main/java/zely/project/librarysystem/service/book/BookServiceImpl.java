package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.book.Author;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.domain.book.Publisher;
import zely.project.librarysystem.dto.book.*;
import zely.project.librarysystem.mapper.BookMapper;
import zely.project.librarysystem.repository.book.BookRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }


    @Override
    public List<BookResponseDto> getAllBooks() {

        List<Book> bookList = bookRepository.findAll();

        return bookList.stream().map(
                book -> {
                    BookResponseDto bookDto = bookMapper.toBookResponseDto(book);
                    bookDto.setAuthorNames(book.getAuthors().stream().map(Author::getAuthorName).collect(Collectors.toSet()));

                    return bookDto;
                }).collect(Collectors.toList());

    }

    @Override
    public Optional<BookResponseDto> getBookById(Integer id) {
        return Optional.ofNullable(bookMapper.toBookDto(bookRepository.findById(id).orElse(null)));

    }

    @Override
    public BookResponseDto createNewBook(BookDto bookDto) {
        return bookMapper.toBookDto(bookRepository.save(bookMapper.toBookEntity(bookDto)));
    }

    @Override
    public Optional<BookResponseDto> updateBookById(Integer id, BookResponseDto bookDto) {
        return bookRepository.findById(id).map(
                foundBook -> {
                    foundBook.setIsbn(bookDto.getIsbn());
                    foundBook.setTitle(bookDto.getTitle());
                    foundBook.setLanguage(bookDto.getLanguage());
                    foundBook.setSubject(bookDto.getSubject());
                    foundBook.setNumberOfPages(bookDto.getNumberOfPages());

                    return foundBook;
                }).map(bookRepository::save).map(bookMapper::toBookResponseDto);
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
    public Optional<BookResponseDto> getBookByIsbn(String isbn) {
        return Optional.ofNullable(bookMapper.toBookDto((bookRepository.getBookByIsbn(isbn)).orElse(null)));

    }
}

