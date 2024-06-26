package zely.project.librarysystem.dto.book;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.config.CustomDoubleSerializer;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.domain.book.Format;
import com.fasterxml.jackson.annotation.JsonFormat;

import zely.project.librarysystem.dto.library.RackDto;

import java.time.LocalDate;


@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BookItemDto {

    private Integer id;

    private String bookBarcode;

    private BookDto book;

    @JsonSerialize(using = CustomDoubleSerializer.class)
    private double price;

    private Format format;

    private RackDto rack;

    private BookStatus bookStatus;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate dateOfPurchase;

    private LocalDate publicationDate;

}