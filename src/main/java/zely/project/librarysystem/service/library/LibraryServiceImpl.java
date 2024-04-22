package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.service.library.LibraryService;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }


    @Override
    public List<Library> getAllLibraries() {
        return (List<Library>)libraryRepository.findAll();

    }
}
