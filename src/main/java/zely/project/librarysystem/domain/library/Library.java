package zely.project.librarysystem.domain.library;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.domain.Book.BookItem;
import zely.project.librarysystem.domain.card.LibraryCard;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "library")
    private Set<LibraryCard> libraryCards = new HashSet<>();;

    @OneToMany(mappedBy = "library")
    private Set<BookItem> bookItems = new HashSet<>();


}
