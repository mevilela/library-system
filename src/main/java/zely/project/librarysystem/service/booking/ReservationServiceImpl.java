package zely.project.librarysystem.service.booking;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.booking.BarcodeReaderDto;
import zely.project.librarysystem.dto.booking.ReservationDto;
import zely.project.librarysystem.manager.ReservationManager;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationManager reservationManager;

    public ReservationServiceImpl(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }
    @Override
    public ReservationDto createReservation(BarcodeReaderDto barcodeReaderDto) {

        return reservationManager.createReservation(barcodeReaderDto);

    }
}
