package zely.project.librarysystem.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorResponseDto {

    private Integer id;

    private String name;

    private int bookCount;

    private Set<Integer> bookIds;

}
