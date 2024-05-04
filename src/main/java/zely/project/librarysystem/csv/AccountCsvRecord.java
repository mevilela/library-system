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
public class AccountCsvRecord {

    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "account_type")
    private String accountType; // Nome correspondente ao CSV

    @CsvBindByName(column = "account_status")
    private String accountStatus;

    @CsvBindByName(column = "password")
    private String password;

    @CsvBindByName(column = "name")
    private String name; // Campos para construir `Person`

    @CsvBindByName(column = "address")
    private String address;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "phone")
    private String phone;

    @CsvBindByName(column = "department")
    private String department;

    @CsvCustomBindByName(converter = LocalDateConverter.class, column = "date_of_membership")
    private LocalDate dateOfMembership;

    @CsvBindByName(column = "total_books_checked_out")
    private Integer totalBooksCheckedOut;
}
