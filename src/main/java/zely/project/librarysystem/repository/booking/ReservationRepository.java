package zely.project.librarysystem.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.booking.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
