package zely.project.librarysystem.domain.card;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.library.Library;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_number")
    private Integer cardNumber;

    @Column(unique = true, nullable = false)
    private String barcode;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate issuedAt;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

}

