package zely.project.librarysystem.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.card.LibraryCard;

public interface LibraryCardRepository extends JpaRepository <LibraryCard, Integer> {
}
