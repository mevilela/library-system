package zely.project.librarysystem.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.domain.book.Format;
import zely.project.librarysystem.dto.book.BookNameAndAuthorDto;

import java.time.LocalDate;


@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BookItemForBookingInfoDto {

    private String bookBarcode;

    private BookNameAndAuthorDto book;

    private Format format;

    private BookStatus bookStatus;

    private LocalDate borrowDate;

    private LocalDate dueDate;



}