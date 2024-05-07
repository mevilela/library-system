package zely.project.librarysystem.domain.Book;

import jakarta.persistence.*;
import lombok.*;
import zely.project.librarysystem.domain.library.Library;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String bookBarcode;

    @Column(nullable = false)
    private String rackLocation;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    private double price;

    private String format;

    private String status;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate dateOfPurchase;

    private LocalDate publicationDate;
}
