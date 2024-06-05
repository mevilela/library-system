package zely.project.librarysystem.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.service.library.LibraryService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public Page<LibraryDto> getAllLibraries(@RequestParam(required = false) String libraryName,
                                            @RequestParam(required = false) Integer pageNumber,
                                            @RequestParam(required = false) Integer pageSize){

        return libraryService.getAllLibraries(libraryName, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public LibraryDto getLibraryById(@PathVariable Integer id) {
        return libraryService.getLibraryById(id)
                .orElseThrow(NotFoundExceptionHandler::new);
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createNewLibrary(@RequestBody LibraryDto libraryDto){

        LibraryDto savedLibrary = libraryService.createNewLibrary(libraryDto);

        return new ResponseEntity<>(savedLibrary, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateLibraryById(@PathVariable Integer id, @RequestBody LibraryDto libraryDto){

        libraryService.updateLibrary(id, libraryDto).orElseThrow(NotFoundExceptionHandler::new);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLibraryById(@PathVariable Integer id){

        if (!libraryService.deleteLibraryById(id)){
            throw new NotFoundExceptionHandler();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
