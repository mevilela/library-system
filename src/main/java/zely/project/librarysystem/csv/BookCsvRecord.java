package zely.project.librarysystem.csv;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zely.project.librarysystem.domain.book.Author;
import zely.project.librarysystem.domain.book.Publisher;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCsvRecord {

    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "isbn")
    private String isbn;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "author_id")
    private Integer authorId;

    @CsvBindByName(column = "publisher_id")
    private Integer publisherId;

    @CsvBindByName(column = "subject")
    private String subject;

    @CsvBindByName(column = "language")
    private String language;

    @CsvBindByName(column = "number_of_pages")
    private Integer numberOfPages;


}




