package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.dto.book.BookItemDto;

@Mapper(componentModel = "spring")
public interface BookItemMapper {

    BookItemDto toBookItemDto(BookItem bookItem);

    BookItem toBookItemEntity(BookItemDto bookItemDto);
}
