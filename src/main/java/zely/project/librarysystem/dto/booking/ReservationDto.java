package zely.project.librarysystem.dto.booking;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.card.LibraryCardDto;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDto {


    private Integer id;

    private LocalDate creationDate;

    private boolean active;

    private BookItemDto bookItem;

    private LibraryCardDto libraryCard;

}
