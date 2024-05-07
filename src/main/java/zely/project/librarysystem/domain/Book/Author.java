package zely.project.librarysystem.domain.Book;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

}
