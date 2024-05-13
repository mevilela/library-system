package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.book.Author;
import zely.project.librarysystem.dto.book.AuthorDto;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toAuthorDto(Author author);

    Author toAuthorEntity(AuthorDto authorDto);

}
