package zely.project.librarysystem.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.card.CardDto;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateReservationDto {

//    private LocalDate localDate;
//
//    private boolean active;

    private String bookItemBarcode;

    private String libraryCardBarcode;

}
