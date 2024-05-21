package zely.project.librarysystem.manager;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.dto.book.BookItemSummaryDto;
import zely.project.librarysystem.dto.booking.CreateReservationDto;
import zely.project.librarysystem.dto.booking.ReservationDto;
import zely.project.librarysystem.dto.card.CardDto;
import zely.project.librarysystem.mapper.ReservationMapper;
import zely.project.librarysystem.repository.booking.ReservationRepository;
import zely.project.librarysystem.service.book.BookItemService;
import zely.project.librarysystem.service.card.CardService;

import java.time.LocalDate;

@Service
public class ReservationManager {
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final CardService cardService;
    private final BookItemService bookItemService;


    public ReservationManager(ReservationMapper reservationMapper, ReservationRepository reservationRepository, CardService cardService, BookItemService bookItemService) {
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
        this.cardService = cardService;
        this.bookItemService = bookItemService;
    }
    @Transactional
    public ReservationDto createReservation(CreateReservationDto createReservationDto) {

        //find bookItem
        BookItemSummaryDto bookItemSummaryDto = bookItemService.getBookItemByBookItemByBarCode(createReservationDto.getBookItemBarcode())
                .orElseThrow(() -> new NotFoundException("BookItem not found"));

        if (bookItemSummaryDto.getBookStatus() == BookStatus.LOST) {
            throw new IllegalStateException("This book is lost");
        }

        //find card
        CardDto cardDto = cardService.getCardByBarcode(createReservationDto.getLibraryCardBarcode())
                .orElseThrow(() -> new NotFoundException("Library card not found"));


        //create reservation
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setBookItem(bookItemSummaryDto);
        reservationDto.setLibraryCard(cardDto);
        reservationDto.setCreationDate(LocalDate.now());
        reservationDto.setActive(true);
        reservationDto.getBookItem().setBookStatus(BookStatus.RESERVED);

        //save reservation
        reservationRepository.save(reservationMapper.toReservationEntity(reservationDto));


        return reservationDto;

    }
}
