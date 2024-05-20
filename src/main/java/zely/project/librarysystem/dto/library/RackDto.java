package zely.project.librarysystem.dto.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RackDto {

    private Integer id;

    private Integer rackNumber;

    private String location;

    private String section;

    private LibraryDto library;

}
