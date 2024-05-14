package zely.project.librarysystem.domain.booking;


import jakarta.persistence.*;
import lombok.*;
import zely.project.librarysystem.domain.book.BookItem;
import zely.project.librarysystem.domain.card.Card;

import java.time.LocalDate;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate startDate;

    private LocalDate returnDate;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "book_item_id")
    private BookItem bookItem;

    @ManyToOne
    @JoinColumn(name = "library_card_id")
    private Card card;

}

