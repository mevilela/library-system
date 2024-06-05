package zely.project.librarysystem.controller.book;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.dto.book.AuthorDto;
import zely.project.librarysystem.dto.book.AuthorResponseDto;
import zely.project.librarysystem.dto.book.AuthorUpdateDto;
import zely.project.librarysystem.service.book.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponseDto> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Integer id){
        return authorService.getAuthorById(id).orElseThrow(NotFoundExceptionHandler::new);
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createNewAuthor(@RequestBody AuthorDto authorDto){

        AuthorDto savedAuthor = authorService.createNewAuthor(authorDto);

        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAuthorById(@PathVariable Integer id, @RequestBody AuthorUpdateDto authorUpdateDto){
        authorService.updateAuthorById(id, authorUpdateDto).orElseThrow(NotFoundExceptionHandler::new);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthorById(@PathVariable Integer id){
        if(!authorService.deleteAuthorById(id)){
            throw new NotFoundExceptionHandler();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
