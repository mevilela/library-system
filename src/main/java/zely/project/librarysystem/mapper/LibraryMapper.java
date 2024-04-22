package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.dto.library.LibraryDto;

@Mapper
public interface LibraryMapper {

    Library libraryToLibraryDto(LibraryDto libraryDto);

    LibraryDto libraryDtoToLibrary(Library library);
}
