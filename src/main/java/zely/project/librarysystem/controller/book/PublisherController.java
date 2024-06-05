package zely.project.librarysystem.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.dto.book.PublisherDto;
import zely.project.librarysystem.service.book.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
@RequiredArgsConstructor
public class PublisherController {
    
    private final PublisherService publisherService;


    @GetMapping
    public List<PublisherDto> getAllPublishers(){
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public PublisherDto getPublisherById(@PathVariable Integer id){
        return publisherService.getPublisherById(id).orElseThrow(NotFoundExceptionHandler::new);
    }

    @PostMapping
    public ResponseEntity<PublisherDto> createNewPublisher(@RequestBody PublisherDto publisherDto){

        PublisherDto savedPublisher = publisherService.createNewPublisher(publisherDto);

        return new ResponseEntity<>(savedPublisher, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updatePublisherById(@PathVariable Integer id, @RequestBody PublisherDto publisherDto){
        publisherService.updatePublisherById(id, publisherDto).orElseThrow(NotFoundExceptionHandler::new);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePublisherById(@PathVariable Integer id){
        if(!publisherService.deletePublisherById(id)){
            throw new NotFoundExceptionHandler();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
