package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.dto.book.BookDto;
import zely.project.librarysystem.dto.book.BookResponseDto;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toBookDto(Book book);

    Book toBookEntity (BookDto bookDto);

//    @Mapping(target = "authorNames", expression = boo)
   BookResponseDto toBookResponseDto(Book book);

}
