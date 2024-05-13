package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.dto.book.BookDto;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toBookDto(Book book);

    Book toBookEntity (BookDto bookDto);


}
