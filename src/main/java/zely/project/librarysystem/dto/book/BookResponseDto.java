package zely.project.librarysystem.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private String isbn;

    private String title;

    private String language;

    private String subject;

    private Integer numberOfPages;

    private Set<String> authorNames;

    private PublisherResponseDto publisher;
}
