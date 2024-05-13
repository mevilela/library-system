package zely.project.librarysystem.domain.booking;


import jakarta.persistence.*;
import lombok.*;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.Member;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.domain.card.LibraryCard;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate creationDate;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "book_item_id")
    private BookItem bookItem;

    @ManyToOne
    @JoinColumn(name = "library_card_id")
    private LibraryCard libraryCard;

}
