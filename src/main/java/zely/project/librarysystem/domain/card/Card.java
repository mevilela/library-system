package zely.project.librarysystem.domain.card;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.booking.Lending;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.domain.library.Library;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "library_card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String barcode;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate issuedAt;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @OneToMany(mappedBy = "card", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Lending> lendings = new HashSet<>();

}

