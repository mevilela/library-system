package zely.project.librarysystem.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCSVRecord {

    @CsvBindByName
    private Integer id;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String address;
}
