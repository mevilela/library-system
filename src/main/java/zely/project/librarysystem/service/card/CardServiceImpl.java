package zely.project.librarysystem.service.card;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.domain.account.Librarian;
import zely.project.librarysystem.domain.account.Member;
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

    @Override
    public Optional<CardDto> updateCardById(Integer id, CardDto cardDto) {
        return cardRepository.findById(id).map(foundCard -> {
                    foundCard.setBarcode(cardDto.getBarcode());
                    foundCard.setIssuedAt(cardDto.getIssuedAt());
                    foundCard.setActive(cardDto.isActive());
                    if (cardDto.getAccount() != null) {
                        Account account = getAccount(cardDto);
                        foundCard.setAccount(account);
                    } else {
                        throw new NullPointerException("Account cannot be null");
                    }

                    return foundCard;
                }).map(cardRepository::save)
                .map(cardMapper::toCardDto);
    }

    private static Account getAccount(CardDto cardDto) {
        AccountDto accountDto = cardDto.getAccount();
        Account account = switch (accountDto.getAccountType()) {
            case MEMBER -> new Member();
            case LIBRARIAN -> new Librarian();
            default -> throw new RuntimeException("Invalid account type");
        };

        account.setId(accountDto.getId());
        return account;
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
