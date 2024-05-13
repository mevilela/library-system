package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Librarian extends Account {
    private java.lang.String department;


}
