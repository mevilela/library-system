package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.card.LibraryCard;
import zely.project.librarysystem.dto.card.LibraryCardDto;
import zely.project.librarysystem.dto.library.LibraryDto;

@Mapper(componentModel = "spring", uses = {AccountMapper.class, MemberMapper.class, LibrarianMapper.class})
public interface LibraryCardMapper {

    LibraryCardDto toLibraryCardDto (LibraryCard libraryCard);

    LibraryCard toLibraryCardEntity(LibraryCardDto libraryCardDto);
}
