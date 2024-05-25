package zely.project.librarysystem.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class BookNameAndAuthorDto {

        private String isbn;

        private String title;

        private Set<String> authorNames;

    }

