package zely.project.librarysystem.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.domain.card.Card;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.dto.card.CardDto;
import zely.project.librarysystem.dto.library.LibraryDto;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository <Card, Integer> {

    List<Card> findCardByLibrary_Id(Integer libraryId);

    Optional<Card> getCardByBarcode(String barcode);

}
