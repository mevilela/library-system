package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;


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
