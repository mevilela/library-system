package zely.project.librarysystem.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zely.project.librarysystem.domain.book.BookStatus;
import zely.project.librarysystem.domain.book.Format;
import zely.project.librarysystem.domain.library.Rack;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookItemCsvRecord {

    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "book_barcode")
    private String bookBarcode;

    @CsvBindByName(column = "book_id")
    private Integer bookId;

    @CsvBindByName(column = "price")
    private double price;

    @CsvBindByName(column = "format")
    private String format;

    @CsvBindByName(column = "rack_id")
    private Integer rackId;

    @CsvBindByName(column = "book_status")
    private String bookStatus;

    @CsvCustomBindByName(converter = LocalDateConverter.class, column = "borrow_date")
    private LocalDate borrowDate;

    @CsvCustomBindByName(converter = LocalDateConverter.class, column = "due_date")
    private LocalDate dueDate;

    @CsvCustomBindByName(converter = LocalDateConverter.class, column = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @CsvCustomBindByName(converter = LocalDateConverter.class, column = "publication_date")
    private LocalDate publicationDate;

}





