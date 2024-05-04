package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Account {

    private LocalDate dateOfMembership;

    private Integer totalBooksCheckedOut;

}
