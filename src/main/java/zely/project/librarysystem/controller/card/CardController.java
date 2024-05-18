package zely.project.librarysystem.controller.card;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.dto.card.CardDto;
import zely.project.librarysystem.service.card.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping
    public List<CardDto> getAllCards(){

        List<CardDto> cardDtos = cardService.getAllCards();

        return ResponseEntity.ok(cardDtos).getBody();
    }

    @GetMapping("/{id}")
    public CardDto getCardById(@PathVariable Integer id){

        return cardService.getCardById(id).orElseThrow(
                () -> new NotFoundException("Card ID not found")
        );
    }



    @PostMapping
    public ResponseEntity<CardDto> createNewCard(@RequestBody CardDto cardDto){

        CardDto cardToBe = cardService.createNewCard(cardDto);

        return new ResponseEntity<>(cardToBe, HttpStatus.CREATED);


    }

    @PutMapping("/{id}")
    public ResponseEntity updateCardById(@PathVariable Integer id, @RequestBody CardDto cardDto){

        cardService.updateCardById(id, cardDto).orElseThrow(
                () -> new NotFoundException("id not found")
        );

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCardById(@PathVariable Integer id){

        if (!cardService.deleteCardById(id)){
            throw new NotFoundException("ID not found");
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



