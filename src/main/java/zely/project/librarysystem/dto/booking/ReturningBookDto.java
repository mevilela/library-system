package zely.project.librarysystem.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.dto.book.BookItemSummaryDto;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReturningBookDto {

    private Integer lendingId;

    private LocalDate transactionDate;

    private String cardBarcode;


    private double fineAmount;


    private BookItemForBookingInfoDto bookItem;

}