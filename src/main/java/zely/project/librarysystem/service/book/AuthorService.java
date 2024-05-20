package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.book.AuthorDto;
import zely.project.librarysystem.dto.book.AuthorResponseDto;
import zely.project.librarysystem.dto.book.AuthorUpdateDto;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {

    List<AuthorResponseDto> getAllAuthors();

    Optional<AuthorDto> getAuthorById(Integer id);

    AuthorDto createNewAuthor(AuthorDto authorDto);

    Optional<AuthorResponseDto> updateAuthorById(Integer id, AuthorUpdateDto authorUpdateDto);

    boolean deleteAuthorById(Integer id);

}

