package zely.project.librarysystem.controller.card;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.webjars.NotFoundException;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountStatus;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.domain.account.Member;
import zely.project.librarysystem.domain.card.Card;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.dto.account.AccountDto;
import zely.project.librarysystem.dto.account.LibrarianDto;
import zely.project.librarysystem.dto.account.MemberDto;
import zely.project.librarysystem.dto.card.CardDto;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.dto.library.RackDto;
import zely.project.librarysystem.mapper.CardMapper;
import zely.project.librarysystem.repository.card.CardRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardControllerIT {

    @Autowired
    CardController cardController;

    @Autowired
    CardMapper cardMapper;

    @Autowired
    CardRepository cardRepository;

    @Test
    void getAllCards() {
        List<CardDto> cardDtos = cardController.getAllCards();

        assertThat(cardDtos.size()).isEqualTo(10);
    }

    @Test
    void testGetCardById() {
        Card card = cardRepository.findAll().get(0);

        CardDto cardDto = cardController.getCardById(card.getId());

        Assertions.assertThat(cardDto).isNotNull();
        Assertions.assertThat(card.getId()).isEqualTo(card.getId());

    }
    @Test
    void getCardByIdIsNotFound() {
        assertThrows(NotFoundException.class, () -> {
            cardController.getCardById(55555);
        });
    }


    @Transactional
    @Rollback
    @Test
    void createNewCard() {

        AccountDto member = new AccountDto();
        member.setAccountType(AccountType.MEMBER);

        CardDto cardDto = new CardDto();
        cardDto.setLibrary(new LibraryDto());
        cardDto.setAccount(member);
        cardDto.setBarcode("12365456");
        cardDto.setActive(true);
        cardDto.setIssuedAt(LocalDate.of(2024,05,11));

        ResponseEntity responseEntity = cardController.createNewCard(cardDto);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        CardDto createdCard = (CardDto) responseEntity.getBody();
        assertThat(createdCard).isNotNull();
    }


    @Transactional
    @Rollback
    @Test
    void updateCardById() {
        Card card = cardRepository.findAll().get(1);
        CardDto cardDto = cardMapper.toCardDto(card);

        AccountDto member = new MemberDto();

        cardDto.setAccount(member);


        ResponseEntity responseEntity = cardController.updateCardById(card.getId(), cardDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Card updatedCard = cardRepository.findById(card.getId()).get();

        assertThat(updatedCard.getAccount().getAccountType()).isEqualTo(AccountType.MEMBER);

    }


    @Transactional
    @Rollback
    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundException.class, () ->
                cardController.updateCardById(2222, null));
    }


    @Transactional
    @Rollback
    @Test
    void deleteCardById() {

        Card card = cardRepository.findAll().get(0);

        ResponseEntity responseEntity = cardController.deleteCardById(card.getId());


        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(cardRepository.findById(card.getId())).isEmpty();


    }


    @Test
    void testDeleteNotFound(){
        assertThrows(NotFoundException.class, () ->
                cardController.deleteCardById(333));
    }
}