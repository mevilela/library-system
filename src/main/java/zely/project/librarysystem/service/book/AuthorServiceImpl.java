package zely.project.librarysystem.service.book;

import zely.project.librarysystem.domain.book.Author;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.dto.book.AuthorDto;
import zely.project.librarysystem.dto.book.BookDto;
import zely.project.librarysystem.mapper.AuthorMapper;
import zely.project.librarysystem.repository.book.AuthorRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public List<AuthorDto> getAllAuthors() {

        List<Author> authorList = authorRepository.findAll();

        return authorList.stream().map(
                authorMapper::toAuthorDto).collect(Collectors.toList());

    }

    @Override
    public Optional<AuthorDto> getAuthorById (Integer id){
        return Optional.ofNullable(authorMapper.toAuthorDto(authorRepository.findById(id).orElse(null)));
    }

    @Override
    public AuthorDto createNewAuthor (AuthorDto authorDto){
        return authorMapper.toAuthorDto(authorRepository.save(authorMapper.toAuthorEntity(authorDto)));
    }

    @Override
    public Optional<AuthorDto> updateAuthorById (Integer id, AuthorDto authorDto){
        return authorRepository.findById(id).map(
                foundAuthor -> {
                    foundAuthor.setAuthorName(authorDto.getName());
                    foundAuthor.setBooks(getBooks(authorDto));

                    return foundAuthor;
                }).map(authorRepository::save).map(authorMapper::toAuthorDto);
    }

    private static Set<Book> getBooks(AuthorDto authorDto) {
        Set<BookDto> bookDtos = authorDto.getBooks;
        Set<Book> books = new HashSet<>();

        for (BookDto bookDto : bookDtos){
            Book book = new Book();
            book.setId(bookDto.getId());

            books.add(book);
        }
        return books;
    }

    @Override
    public boolean deleteAuthorById (Integer id){
        if(authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return true;
        }
        return false;    }
}

