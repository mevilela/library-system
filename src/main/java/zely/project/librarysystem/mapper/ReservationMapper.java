package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.dto.booking.CreateReservationDto;
import zely.project.librarysystem.dto.booking.ReservationDto;

@Mapper(componentModel = "spring", uses = {BookItemMapper.class, AccountMapper.class, MemberMapper.class, LibrarianMapper.class})
public interface ReservationMapper {

    ReservationDto toReservationDto(Reservation reservation);

    Reservation toReservationEntity(ReservationDto reservationDto);


    CreateReservationDto toCreateReservationDto(Reservation reservation);

    @Mapping(target = "bookItem", source = "bookItemBarcode")
    Reservation toReservationFromCreate(CreateReservationDto createReservationDto);


    default BookItem stringToBookItem(String bookItemBarcode){

        BookItem bookItem = new BookItem();
        bookItem.setBookBarcode(bookItemBarcode);

        return bookItem;
    }
}
