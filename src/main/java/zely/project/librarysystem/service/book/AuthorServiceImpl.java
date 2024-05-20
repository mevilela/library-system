package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.book.Author;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.dto.book.AuthorDto;
import zely.project.librarysystem.dto.book.AuthorResponseDto;
import zely.project.librarysystem.dto.book.AuthorUpdateDto;
import zely.project.librarysystem.mapper.AuthorMapper;
import zely.project.librarysystem.repository.book.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public List<AuthorResponseDto> getAllAuthors() {

        List<Author> authorList = authorRepository.findAll();

        return authorList.stream()
                        .map(author -> {
                            AuthorResponseDto dto = authorMapper.toAuthorResponseDto(author);
                            dto.setBookCount(author.getBooks().size());
                            dto.setBookIds(author.getBooks().stream().map(Book::getId).collect(Collectors.toSet()));
                            return dto;
                        })
                        .collect(Collectors.toList());

    }

    @Override
    public Optional<AuthorDto> getAuthorById (Integer id){
        return Optional.ofNullable(authorMapper.toAuthorDto(authorRepository.findById(id).orElse(null)));
    }

    @Override
    public AuthorDto createNewAuthor (AuthorDto authorDto){
        Author author = authorMapper.toAuthorEntity(authorDto);
//        Set<Book> books = getBooks((AuthorDto) authorDto.getBooks());
//        author.setBooks(books);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toAuthorDto(savedAuthor);    }

    @Override
    public Optional<AuthorResponseDto> updateAuthorById (Integer id, AuthorUpdateDto authorUpdateDto){
        return authorRepository.findById(id).map(
                foundAuthor -> {
                    foundAuthor.setAuthorName(authorUpdateDto.getName());
                    return foundAuthor;
                }).map(authorRepository::save).map(authorMapper::toAuthorResponseDto);
    }

//    private static Set<Book> getBooks(AuthorDto authorDto) {
//
//        Set<BookDto> bookDtos = authorDto.getBooks();
//        Set<Book> books = new HashSet<>();
//
//        for (BookDto bookDto : bookDtos){
//            Book book = new Book();
//            book.setId(bookDto.getId());
//
//            books.add(book);
//        }
//        return books;
//    }

    @Override
    public boolean deleteAuthorById (Integer id){
        if(authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return true;
        }
        return false;    }
}

