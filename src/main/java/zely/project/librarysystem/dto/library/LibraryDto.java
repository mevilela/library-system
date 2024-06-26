package zely.project.librarysystem.dto.library;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LibraryDto {
    private Integer id;

    private String name;

    private String address;

}
