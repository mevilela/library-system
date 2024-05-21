package zely.project.librarysystem.controller.booking;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.project.librarysystem.dto.booking.CreateReservationDto;
import zely.project.librarysystem.dto.booking.ReservationDto;
import zely.project.librarysystem.service.booking.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping("/")
    public ResponseEntity createReservation(@RequestBody CreateReservationDto createReservationDto){

        ReservationDto reservationSaved = reservationService.createReservation(createReservationDto);

        return new ResponseEntity<>(reservationSaved, HttpStatus.CREATED);

    }
}
