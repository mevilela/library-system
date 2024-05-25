package zely.project.librarysystem.service.booking;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.booking.BarcodeReaderDto;
import zely.project.librarysystem.dto.booking.ReservationDto;

@Service
public interface ReservationService {

    ReservationDto createReservation(BarcodeReaderDto barcodeReaderDto);
}
