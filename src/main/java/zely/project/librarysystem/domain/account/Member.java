package zely.project.librarysystem.domain.account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import zely.project.librarysystem.domain.booking.Lending;
import zely.project.librarysystem.domain.booking.Reservation;
import zely.project.librarysystem.domain.card.LibraryCard;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Account {

    private LocalDate dateOfMembership;

    private Integer totalBooksCheckedOut;


}
