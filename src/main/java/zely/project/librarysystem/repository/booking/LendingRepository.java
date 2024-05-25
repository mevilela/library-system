package zely.project.librarysystem.repository.booking;
import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.domain.booking.Lending;

import java.util.Optional;

public interface LendingRepository extends JpaRepository<Lending, Integer> {

}
