package zely.project.librarysystem.service.booking;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.booking.CreateReservationDto;
import zely.project.librarysystem.dto.booking.ReservationDto;

@Service
public interface ReservationService {

    ReservationDto createReservation(CreateReservationDto createReservationDto);
}
