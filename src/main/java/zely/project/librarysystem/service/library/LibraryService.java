package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.library.Library;

import java.util.List;

@Service
public interface LibraryService {

    List<Library> getAllLibraries();
}
