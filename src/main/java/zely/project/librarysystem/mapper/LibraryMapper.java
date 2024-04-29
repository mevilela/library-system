package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.dto.library.LibraryDto;

@Mapper(componentModel = "spring")
public interface LibraryMapper {

    Library toLibraryEntity(LibraryDto libraryDto);

    LibraryDto toLibraryDto(Library library);
}

