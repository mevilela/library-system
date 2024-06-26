package zely.project.librarysystem.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDto {

    private String isbn;

    private Set<Integer> authorIdSet;

    private Integer publisherId;

    private String title;

    private String language;

    private String subject;

    private Integer numberOfPages;

}

