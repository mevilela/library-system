package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;


@Entity
@Getter
@Builder
@Setter

public class Member extends Account {

    private LocalDate dateOfMembership;

    private Integer totalBooksCheckedOut;


    public Member() {
        super();
        this.setAccountType(AccountType.MEMBER);
    }


    public Member(LocalDate dateOfMembership, Integer totalBooksCheckedOut) {
        super();
        this.dateOfMembership = dateOfMembership;
        this.setAccountType(AccountType.MEMBER);
    }

}
