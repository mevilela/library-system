package zely.project.librarysystem.dto.booking;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.book.BookItemSummaryDto;
import zely.project.librarysystem.dto.card.CardDto;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDto {

    private LocalDate creationDate;

    private boolean active;

    private BookItemSummaryDto bookItem;

}
