package zely.project.librarysystem.service.card;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.domain.card.Card;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.dto.account.AccountDto;
import zely.project.librarysystem.dto.card.CardDto;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.mapper.CardMapper;
import zely.project.librarysystem.repository.card.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public List<CardDto> getAllCards() {

        List<Card> cards = cardRepository.findAll();

        return cards.stream().map(
                cardMapper::toCardDto
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<CardDto> getCardById(Integer id) {

        return Optional.ofNullable(cardMapper.toCardDto(cardRepository.findById(id).orElse(null)));
    }

    @Override
    public List<CardDto> getCardByLibrary(Integer id) {

        List<Card> cards = cardRepository.findCardByLibrary_Id(id);

        List<CardDto> cardDtos = new ArrayList<>();

        for (Card card : cards){
            cardDtos.add(cardMapper.toCardDto(card));
        }

        return cardDtos;

    }

    @Override
    public CardDto createNewCard(CardDto cardDto) {

        return cardMapper.toCardDto(cardRepository.save(cardMapper.toCardEntity(cardDto)));

    }

    @Override //todo
    public Optional<CardDto> updateCardById(Integer id, CardDto cardDto) {

//        Card dtoToCard = cardMapper.toCardEntity(cardDto);
//
//        Account account = dtoToCard.getAccount();
//        Library library = dtoToCard.getLibrary();

        return cardRepository.findById(id).map(
                        foundCard -> {
                            foundCard.setBarcode(cardDto.getBarcode());
                            foundCard.setIssuedAt(cardDto.getIssuedAt());

                            return foundCard;
                        }).map(cardRepository::save)
                .map(cardMapper::toCardDto);



//        Optional<CardDto> optionalCardDto = cardRepository.findById(id)
//                .map(foundCard -> {
//                    foundCard.setActive(cardToBe.isActive());
//                    foundCard.setIssuedAt(cardToBe.getIssuedAt());
////                    foundCard.setAccount(cardToBe.getAccount().setAccountType(AccountType.MEMBER));
//                    foundCard.setLibrary(cardToBe.getLibrary());
//
//                    return cardMapper.toCardDto(foundCard);
//                });
//
//        Card cardToBeSaved = cardMapper.toCardEntity(optionalCardDto.get());
//
//        cardRepository.save(cardToBeSaved);
//
//        return Optional.ofNullable(cardMapper.toCardDto(cardToBeSaved));

    }

    @Override
    public boolean deleteCardById(Integer id) {

        if(cardRepository.existsById(id)){
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
