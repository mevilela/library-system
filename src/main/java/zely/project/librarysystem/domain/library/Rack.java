package zely.project.librarysystem.domain.library;


import jakarta.persistence.*;
import lombok.*;
import zely.project.librarysystem.domain.book.BookItem;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rack_number", nullable = false, unique = true)
    private Integer rackNumber;

    private String location;

    private String section;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @Builder.Default
    @OneToMany(mappedBy = "rack")
    private Set<BookItem> bookItemSet = new HashSet<>();
}
