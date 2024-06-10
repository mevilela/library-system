package zely.project.librarysystem.service.booking;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.booking.BarcodeReaderDto;
import zely.project.librarysystem.dto.booking.ReservationDto;

import java.util.Optional;

@Service
public interface ReservationService {

    ReservationDto createReservation(BarcodeReaderDto barcodeReaderDto);


}
