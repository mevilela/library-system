package zely.project.librarysystem.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherCsvRecord {


    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "publisher_name")
    private String publisherName;

}
