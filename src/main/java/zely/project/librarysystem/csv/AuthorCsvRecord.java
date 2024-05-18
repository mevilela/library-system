package zely.project.librarysystem.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCsvRecord {

    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "author_name")
    private String authorName;

}
