package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.dto.booking.ReservationDto;

@Mapper(componentModel = "spring", uses = {AccountMapper.class, MemberMapper.class, LibrarianMapper.class})
public interface ReservationMapper {

    ReservationDto toReservationDto(Reservation reservation);

    Reservation toReservationEntity(ReservationDto reservationDto);
}
