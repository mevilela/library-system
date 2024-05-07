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
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();

}
