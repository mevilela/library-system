package zely.project.librarysystem.dto.booking;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.dto.book.BookItemDto;
import zely.project.librarysystem.dto.card.LibraryCardDto;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LendingDto {

    private Integer id;

    private LocalDate startDate;

    private LocalDate returnDate;

    private boolean active;

    private BookItemDto bookItem;

    private LibraryCardDto libraryCard;

}

