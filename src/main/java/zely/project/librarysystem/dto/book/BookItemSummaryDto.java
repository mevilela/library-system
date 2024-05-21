package zely.project.librarysystem.dto.book;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.config.CustomDoubleSerializer;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.domain.book.Format;

import java.time.LocalDate;


@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BookItemSummaryDto {

    private String bookBarcode;

    private BookSummaryDto book;

    @JsonSerialize(using = CustomDoubleSerializer.class)
    private double price;

    private Format format;

    private Integer rack;

    private String library;

    private BookStatus bookStatus;

    private LocalDate dateOfPurchase;

    private LocalDate publicationDate;

}