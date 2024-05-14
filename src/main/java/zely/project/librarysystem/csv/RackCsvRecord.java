package zely.project.librarysystem.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RackCsvRecord {

    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "library_code")
    private Integer libraryCode;

    @CsvBindByName(column = "rack_number")
    private Integer rackNumber;

    @CsvBindByName(column = "location")
    private String location;

    @CsvBindByName(column = "section")
    private String section;


}
