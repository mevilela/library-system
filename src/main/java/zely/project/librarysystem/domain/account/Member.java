package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.domain.card.LibraryCard;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Account {

    private LocalDate dateOfMembership;

    private Integer totalBooksCheckedOut;

}
