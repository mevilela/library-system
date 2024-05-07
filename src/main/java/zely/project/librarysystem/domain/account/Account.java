package zely.project.librarysystem.domain.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.domain.card.LibraryCard;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private java.lang.String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Embedded
    private Person person;

    @OneToMany(mappedBy = "account")
    private Set<LibraryCard> libraryCards;


    public Account() {
    }

    public Account(Integer id, String password, AccountType accountType, AccountStatus accountStatus, Person person, Set<LibraryCard> libraryCards) {
        this.id = id;
        this.password = password;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.person = person;
        this.libraryCards = libraryCards;
    }
}
