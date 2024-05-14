package zely.project.librarysystem.csv;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardCsvRecord {

    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "barcode")
    private String barcode;

    @CsvCustomBindByName(converter = LocalDateConverter.class, column = "issued_at")
    private LocalDate issuedAt;

    @CsvBindByName(column = "active")
    private boolean active;

    @CsvBindByName(column = "account_id")
    private Integer accountId;

    @CsvBindByName(column = "library_id")
    private Integer libraryId;


}


