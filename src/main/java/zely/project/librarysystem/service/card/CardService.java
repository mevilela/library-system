package zely.project.librarysystem.service.card;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.card.CardDto;

import java.util.List;
import java.util.Optional;

@Service
public interface CardService {

    List<CardDto> getAllCards();

    Optional<CardDto> getCardById(Integer id);
    Optional<CardDto> getCardByBarcode(String cardBarcode);

    List<CardDto> getCardByLibrary(Integer id);

    CardDto createNewCard(CardDto cardDto);

    Optional<CardDto> updateCardById(Integer id, CardDto cardDto);

    boolean deleteCardById(Integer id);
}
