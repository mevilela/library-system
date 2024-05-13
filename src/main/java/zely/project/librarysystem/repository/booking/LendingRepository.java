package zely.project.librarysystem.repository.booking;
import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.booking.Lending;

public interface LendingRepository extends JpaRepository<Lending, Integer> {
}
