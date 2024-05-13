package zely.project.librarysystem.domain.book;

import jakarta.persistence.*;
import lombok.*;
import zely.project.librarysystem.domain.booking.Lending;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.domain.library.Rack;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "book_barcode",unique = true, nullable = false)
    private String bookBarcode;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private double price;

    @Enumerated(EnumType.STRING)
    private Format format;

    @ManyToOne
    @JoinColumn(name = "rack_id", nullable = false)
    private Rack rack;

    @Column(name = "book_status")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @OneToMany(mappedBy = "bookItem", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "bookItem", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Lending> lendings = new HashSet<>();

}
