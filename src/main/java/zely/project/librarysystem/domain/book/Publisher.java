package zely.project.librarysystem.domain.book;

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

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

}
