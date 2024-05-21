package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.book.BookItemSummaryDto;
import zely.project.librarysystem.dto.book.BookSummaryDto;

@Mapper(componentModel = "spring", uses = {LibraryMapper.class, RackMapper.class, AuthorMapper.class, BookMapper.class})
public interface BookItemMapper {

    BookItemDto toBookItemDto(BookItem bookItem);

    BookItem toBookItemEntity(BookItemDto bookItemDto);
    @Mapping(target = "rack", source = "rack.rackNumber")
    @Mapping(target = "library", source = "rack.library.name")
    @Mapping(target = "bookStatus", source="status")
    BookItemSummaryDto toBookItemSummaryDto(BookItem bookItem);
    @Mapping(target = "rack.rackNumber", source = "rack")
    @Mapping(target = "rack.library.name", source = "rack")
    @Mapping(target = "status", source="bookStatus")
    BookItem toBookItemFromSummaryDto(BookItemSummaryDto bookItemSummaryDto);


}
