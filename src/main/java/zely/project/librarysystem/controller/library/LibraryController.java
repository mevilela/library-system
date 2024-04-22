package zely.project.librarysystem.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.service.library.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {


    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<List<Library>> getLibrary(){

        return ResponseEntity.ok(libraryService.getAllLibraries());

    }

}
