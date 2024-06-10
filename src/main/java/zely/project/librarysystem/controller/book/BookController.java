package zely.project.librarysystem.controller.book;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.dto.book.BookCreateDto;
import zely.project.librarysystem.dto.book.BookDto;
import zely.project.librarysystem.service.book.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/id/{id}")
    public BookDto getBookById(@PathVariable Integer id){
        return bookService.getBookById(id).orElseThrow(NotFoundExceptionHandler::new);
    }

    @GetMapping("/isbn/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn).orElseThrow(NotFoundExceptionHandler::new);
    }

    @GetMapping("/subject/{subject}")
    public BookDto getBookBySubject(@PathVariable String subject){
        return bookService.getBookBySubject(subject).orElseThrow(NotFoundExceptionHandler::new);
    }

    @PostMapping
    public ResponseEntity<BookCreateDto> createNewBook(@RequestBody BookCreateDto bookCreateDto){

        BookCreateDto savedBook = bookService.createNewBook(bookCreateDto);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBookById(@PathVariable Integer id, @RequestBody BookDto bookDto){
        bookService.updateBookById(id, bookDto).orElseThrow(NotFoundExceptionHandler::new);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBookById(@PathVariable Integer id){
        if(!bookService.deleteBookById(id)){
            throw new NotFoundExceptionHandler();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
