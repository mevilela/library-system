package zely.project.librarysystem.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.dto.library.LibraryDto;
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
    public List<LibraryDto> getAllLibraries(){

        return libraryService.getAllLibraries();
    }

    @GetMapping("/{id}")
    public LibraryDto getLibraryById(@PathVariable Integer id) {
        return libraryService.getLibraryById(id)
                .orElseThrow(() -> new NotFoundException("id not found"));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createNewLibrary(@RequestBody LibraryDto libraryDto){

        LibraryDto savedLibrary = libraryService.createNewLibrary(libraryDto);

        return new ResponseEntity<>(savedLibrary, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateLibraryById(@PathVariable Integer id, @RequestBody LibraryDto libraryDto){

        libraryService.updateLibrary(id, libraryDto).orElseThrow(() -> new NotFoundException("Library not found"));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLibraryById(@PathVariable Integer id){

        if (!libraryService.deleteLibraryById(id)){
            throw new NotFoundException("id not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
