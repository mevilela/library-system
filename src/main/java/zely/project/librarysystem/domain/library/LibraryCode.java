package zely.project.librarysystem.domain.library;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCode {

    @Id
    @Column(name = "code_id")
    private Integer codeId;

    @OneToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @OneToMany(mappedBy = "libraryCode", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Rack> racks = new HashSet<>();



}
