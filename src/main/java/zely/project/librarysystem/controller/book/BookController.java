package zely.project.librarysystem.controller.book;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.dto.book.BookDto;
import zely.project.librarysystem.dto.book.BookResponseDto;
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
    public List<BookResponseDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/id/{id}")
    public BookDto getBookById(@PathVariable Integer id){
        return bookService.getBookById(id).orElseThrow(() -> new NotFoundException("id not found"));
    }

    @GetMapping("/isbn/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn){
        return bookService.getBookByIsbn(isbn).orElseThrow(() -> new NotFoundException("id not found"));
    }

    @PostMapping
    public ResponseEntity<BookDto> createNewBook(@RequestBody BookDto bookDto){

        BookDto savedBook = bookService.createNewBook(bookDto);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateBookById(@PathVariable Integer id, @RequestBody BookDto bookDto){
        bookService.updateBookById(id, bookDto).orElseThrow(() -> new NotFoundException("book not found"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBookById(@PathVariable Integer id){
        if(!bookService.deleteBookById(id)){
            throw new NotFoundException("book not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}