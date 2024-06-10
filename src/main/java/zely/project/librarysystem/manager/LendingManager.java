package zely.project.librarysystem.manager;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.domain.booking.Lending;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.domain.card.Card;
import zely.project.librarysystem.dto.booking.BarcodeReaderDto;
import zely.project.librarysystem.dto.booking.LendingDto;
import zely.project.librarysystem.dto.booking.ReturningBookDto;
import zely.project.librarysystem.mapper.BookItemMapper;
import zely.project.librarysystem.mapper.LendingMapper;
import zely.project.librarysystem.mapper.ReservationMapper;
import zely.project.librarysystem.repository.book.BookItemRepository;
import zely.project.librarysystem.repository.booking.LendingRepository;
import zely.project.librarysystem.repository.booking.ReservationRepository;
import zely.project.librarysystem.repository.card.CardRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Set;


@Service
public class LendingManager {

    private final BookItemRepository bookItemRepository;
    private final LendingRepository lendingRepository;

    private final CardRepository cardRepository;

    private final LendingMapper lendingMapper;
    private final BookItemMapper bookItemMapper;

    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;

    public LendingManager(BookItemRepository bookItemRepository, LendingRepository lendingRepository, CardRepository cardRepository, LendingMapper lendingMapper, BookItemMapper bookItemMapper, ReservationMapper reservationMapper, ReservationRepository reservationRepository) {
        this.bookItemRepository = bookItemRepository;
        this.lendingRepository = lendingRepository;
        this.cardRepository = cardRepository;
        this.lendingMapper = lendingMapper;
        this.bookItemMapper = bookItemMapper;
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
    }

    public LendingDto createNewLoan(BarcodeReaderDto barcodeReaderDto) {

        BookItem bookItem = bookItemRepository.getBookItemByBookBarcode(barcodeReaderDto.getBookItemBarcode())
                .orElseThrow(() -> new NotFoundExceptionHandler("BookItem not found"));

        Card card = cardRepository.getCardByBarcode(barcodeReaderDto.getLibraryCardBarcode())
                .orElseThrow(() -> new NotFoundExceptionHandler("LibraryCard not found"));

        if (bookItem.getStatus().equals(BookStatus.LOANED)){
            throw new RuntimeException("This book is Loaned");
        }

        if (bookItem.getStatus().equals(BookStatus.LOST)){
            throw new RuntimeException("This book is not available");
        }

        if(bookItem.getStatus().equals(BookStatus.RESERVED)){

            boolean validReservation = false;

            for (Reservation reservation : bookItem.getReservations()){
                if(reservation.getCard().equals(card)){
                    validReservation = true;
                }
                break;
            }

            if (!validReservation) {
                throw new RuntimeException("This book is reserved for another card");
            }

        }

        Lending lending = new Lending();
        lending.setCard(card);
        lending.setBookItem(bookItem);
        lending.setStartDate(LocalDate.now());
        lending.setActive(true);

        bookItem.setBorrowDate(lending.getStartDate());
        bookItem.setDueDate(lending.getStartDate().plusDays(15));
        bookItem.setStatus(BookStatus.LOANED);

        checkOutBook(card);


        bookItemRepository.save(bookItem);

        cardRepository.save(card);

        lendingRepository.save(lending);


        return lendingMapper.toLendingDto(lending);


    }

    private void checkOutBook(Card card) {

        Integer totalBooksCheckedOut = card.getAccount().getTotalBooksCheckedOut();

        if (totalBooksCheckedOut == null){
            totalBooksCheckedOut = 0;
        }

        if (totalBooksCheckedOut > 5) {
            throw new RuntimeException("You can't loan more books");
        } else {
            totalBooksCheckedOut ++;
        }
        card.getAccount().setTotalBooksCheckedOut(totalBooksCheckedOut);
        cardRepository.save(card);
    }




    public ReturningBookDto returningBook(BarcodeReaderDto barcodeReaderDto) {

        ReturningBookDto returningBookDto = new ReturningBookDto();


        BookItem bookItem = bookItemRepository.getBookItemByBookBarcode(barcodeReaderDto.getBookItemBarcode())
                .orElseThrow(() -> new NotFoundException("BookItem not found"));

        Card card = cardRepository.getCardByBarcode(barcodeReaderDto.getLibraryCardBarcode())
                .orElseThrow(() -> new RuntimeException("LibraryCard not found"));

        Lending lending = card.getLendings().stream()
                .filter(foundLending -> foundLending.getBookItem().equals(bookItem))
                .max(Comparator.comparing(Lending::getStartDate)).orElseThrow(() -> new RuntimeException("Lending not found"));

        returningBookDto.setLendingId(lending.getId());
        returningBookDto.setCardBarcode(card.getBarcode());
        returningBookDto.setTransactionDate(LocalDate.now());
        returningBookDto.setBookItem(bookItemMapper.toBookItemForBooking(bookItem));



        // testing fine scenario
        //bookItem.setDueDate(LocalDate.of(2024,05,20));

        if (LocalDate.now().isAfter(bookItem.getDueDate())) {
            double totalFine = calculateFine(bookItem);
            returningBookDto.setFineAmount(totalFine);

            return returningBookDto;
        }

        bookItem.setStatus(BookStatus.AVAILABLE);
        lending.setReturnDate(LocalDate.now());
        checkInBook(card);

        returningBookDto.setFineAmount(0.00);

        bookItemRepository.save(bookItem);
        lendingRepository.save(lending);
        cardRepository.save(card);


        return returningBookDto;


    }

    private void checkInBook(Card card) {

        Integer totalBooksCheckedOut = card.getAccount().getTotalBooksCheckedOut();

        if (totalBooksCheckedOut == null){
            totalBooksCheckedOut = 0;
        }

        if (totalBooksCheckedOut > 0) {
            totalBooksCheckedOut--;
        }

        card.getAccount().setTotalBooksCheckedOut(totalBooksCheckedOut);
        cardRepository.save(card);

    }


    private double calculateFine(BookItem bookItem) {

        double overdueDays = (double) bookItem.getDueDate().datesUntil(LocalDate.now()).count();

        double finePerDay = 2.5;

        double totalFine = overdueDays * finePerDay;

        return totalFine;

    }


}

















