package zely.project.librarysystem.service.library;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.library.LibraryDto;

import java.util.Optional;

@Service
public interface LibraryService {

    Page<LibraryDto> getAllLibraries(String libraryName, Integer pageNumber, Integer pageSize);

    Optional<LibraryDto> getLibraryById(Integer id);

    LibraryDto createNewLibrary(LibraryDto libraryDto);

    Optional<LibraryDto> updateLibrary(Integer id, LibraryDto libraryDto);

    Boolean deleteLibraryById(Integer id);
}
