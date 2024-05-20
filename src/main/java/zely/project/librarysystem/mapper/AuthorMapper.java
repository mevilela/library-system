package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.book.Author;
import zely.project.librarysystem.dto.book.AuthorDto;
import zely.project.librarysystem.dto.book.AuthorResponseDto;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface AuthorMapper {

    @Mapping(target = "name", source = "author.authorName")
    AuthorDto toAuthorDto(Author author);

    @Mapping(target = "authorName", source = "authorDto.name")
    Author toAuthorEntity(AuthorDto authorDto);

    @Mapping(target = "name", source = "author.authorName")
    AuthorResponseDto toAuthorResponseDto(Author author);

}
