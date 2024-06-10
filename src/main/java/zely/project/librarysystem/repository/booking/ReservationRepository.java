package zely.project.librarysystem.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.dto.booking.ReservationDto;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
