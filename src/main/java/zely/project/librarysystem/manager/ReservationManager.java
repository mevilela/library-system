package zely.project.librarysystem.manager;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.domain.card.Card;
import zely.project.librarysystem.dto.booking.BarcodeReaderDto;
import zely.project.librarysystem.dto.booking.ReservationDto;
import zely.project.librarysystem.mapper.ReservationMapper;
import zely.project.librarysystem.repository.book.BookItemRepository;
import zely.project.librarysystem.repository.booking.ReservationRepository;
import zely.project.librarysystem.repository.card.CardRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationManager {
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final BookItemRepository bookItemRepository;
    private final CardRepository cardRepository;

    public ReservationManager(ReservationMapper reservationMapper, ReservationRepository reservationRepository, BookItemRepository bookItemRepository, CardRepository cardRepository) {
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
        this.bookItemRepository = bookItemRepository;
        this.cardRepository = cardRepository;
    }
    @Transactional
    public ReservationDto createReservation(BarcodeReaderDto barcodeReaderDto) {

        //find bookItem
        BookItem bookItem = bookItemRepository.getBookItemByBookBarcode(barcodeReaderDto.getBookItemBarcode())
                .orElseThrow(() -> new NotFoundExceptionHandler("BookItem not found"));

        if (bookItem.getStatus() == BookStatus.LOST) {
            throw new RuntimeException("This book is not available");
        }

        //find card
        Card card = cardRepository.getCardByBarcode(barcodeReaderDto.getLibraryCardBarcode())
                .orElseThrow(() -> new NotFoundExceptionHandler("Library card not found"));


        //create reservation
        Reservation reservation = new Reservation();
        reservation.setBookItem(bookItem);
        reservation.setCreationDate(LocalDate.now());
        reservation.setCard(card);
        reservation.setActive(true);

        bookItem.setStatus(BookStatus.RESERVED);

        bookItemRepository.save(bookItem);

        // Save reservation
        reservationRepository.save(reservation);


        return reservationMapper.toReservationDto(reservation);


    }


}
