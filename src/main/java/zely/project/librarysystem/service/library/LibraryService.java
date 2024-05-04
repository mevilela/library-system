package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.library.LibraryDto;

import java.util.List;
import java.util.Optional;

@Service
public interface LibraryService {

    List<LibraryDto> getAllLibraries(String libraryName);

    Optional<LibraryDto> getLibraryById(Integer id);

    LibraryDto createNewLibrary(LibraryDto libraryDto);

    Optional<LibraryDto> updateLibrary(Integer id, LibraryDto libraryDto);

    Boolean deleteLibraryById(Integer id);
}
