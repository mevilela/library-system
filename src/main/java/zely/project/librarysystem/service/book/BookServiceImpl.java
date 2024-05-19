package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.book.Author;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.domain.book.Publisher;
import zely.project.librarysystem.dto.book.AuthorDto;
import zely.project.librarysystem.dto.book.BookDto;
import zely.project.librarysystem.dto.book.PublisherDto;
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
    public List<BookDto> getAllBooks() {

        List<Book> bookList = bookRepository.findAll();

        return bookList.stream().map(
                bookMapper::toBookDto).collect(Collectors.toList());

    }

    @Override
    public Optional<BookDto> getBookById(Integer id) {
        return Optional.ofNullable(bookMapper.toBookDto(bookRepository.findById(id).orElse(null)));

    }

    @Override
    public BookDto createNewBook(BookDto bookDto) {
        return bookMapper.toBookDto(bookRepository.save(bookMapper.toBookEntity(bookDto)));
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
                    if (foundBook.getPublisher() != null){
                        Publisher publisher = getPublisher(bookDto);
                        foundBook.setPublisher(publisher);
                    } else {
                        throw new NullPointerException("Publisher cannot be null");
                    }

                    if (foundBook.getAuthors() != null){
                        Set<Author> authors = getAuthors(bookDto);
                        foundBook.setAuthors(authors);
                    } else {
                        throw new NullPointerException("Author cannot be null");
                    }

                    return foundBook;
                }).map(bookRepository::save).map(bookMapper::toBookDto);
    }

    private Set<Author> getAuthors(BookDto bookDto){

        Set<AuthorDto> authorDtos = bookDto.getAuthors();

        Set<Author> authors = new HashSet<>();

        for (AuthorDto authorDto : authorDtos) {
            Author author = new Author();
            author.setId(authorDto.getId());
            authors.add(author);
        }

        return authors;
    }

    private Publisher getPublisher(BookDto bookDto){

        PublisherDto publisherDto = bookDto.getPublisher();
        Publisher publisher = new Publisher();
        publisher.setId(publisherDto.getId());

        return publisher;
    }

    @Override
    public boolean deleteBookById(Integer id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

