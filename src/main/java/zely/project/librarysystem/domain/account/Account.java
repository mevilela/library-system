package zely.project.librarysystem.domain.account;

import jakarta.persistence.*;
import lombok.*;
import zely.project.librarysystem.domain.card.Card;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private Integer totalBooksCheckedOut;

    @OneToMany(mappedBy = "account", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Card> cards = new HashSet<>();;

}
