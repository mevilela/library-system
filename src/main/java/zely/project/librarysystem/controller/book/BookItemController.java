package zely.project.librarysystem.controller.book;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.book.BookItemSummaryDto;
import zely.project.librarysystem.service.book.BookItemService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/book-item")
public class BookItemController {

    private final BookItemService bookItemService;

    public BookItemController(BookItemService bookItemService) {
        this.bookItemService = bookItemService;
    }


    @GetMapping
    public List<BookItemSummaryDto> getAllBookItems(){
        return bookItemService.getAllBookItems();
    }

    @GetMapping("/id/{id}")
    public BookItemSummaryDto getBookItemById(@PathVariable Integer id){
        return bookItemService.getBookItemById(id).orElseThrow(NotFoundExceptionHandler::new);
    }

    @GetMapping("/barcode/{bookBarcode}")
    public BookItemSummaryDto getBookItemByBookBarcode(@PathVariable String bookBarcode){
        return bookItemService.getBookItemByBookItemByBarCode(bookBarcode).orElseThrow(NotFoundExceptionHandler::new);
    }
    @GetMapping("/publication-date/{date}")
    public BookItemSummaryDto getBookItemByPublicationDate(@PathVariable LocalDate date){
        return bookItemService.getBookItemByPublicationDate(date).orElseThrow(NotFoundExceptionHandler::new);
    }


    @PostMapping
    public ResponseEntity<BookItemDto> createNewBookItem(@RequestBody BookItemDto bookItemDto){

        BookItemDto savedBookItem = bookItemService.createNewBookItem(bookItemDto);

        return new ResponseEntity<>(savedBookItem, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateBookItemById(@PathVariable Integer id, @RequestBody BookItemDto bookItemDto){
        bookItemService.updateBookItemById(id, bookItemDto).orElseThrow(NotFoundExceptionHandler::new);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBookItemById(@PathVariable Integer id){
        if(!bookItemService.deleteBookItemById(id)){
            throw new NotFoundExceptionHandler();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
