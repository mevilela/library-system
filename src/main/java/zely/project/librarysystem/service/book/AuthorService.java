package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.book.AuthorDto;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {

    List<AuthorDto> getAllAuthors();

    Optional<AuthorDto> getAuthorById(Integer id);

    AuthorDto createNewAuthor(AuthorDto authorDto);

    Optional<AuthorDto> updateAuthorById(Integer id, AuthorDto authorDto);

    boolean deleteAuthorById(Integer id);

}

