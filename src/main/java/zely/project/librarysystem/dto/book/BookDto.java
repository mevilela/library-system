package zely.project.librarysystem.dto.book;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.domain.book.Publisher;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer id;

    private String isbn;

    private String title;

    private String language;

    private String subject;

    private Integer numberOfPages;

    private Set<String> authorNames;

    private String publisherName;
}


