package zely.project.librarysystem.service.account;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.AccountCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class AccountCsvServiceImpl implements AccountCsvService {
    @Override
    public List<AccountCsvRecord> convertCSV(File cvsFile) {

        try {
            List<AccountCsvRecord> accountCsvRecords = new CsvToBeanBuilder<AccountCsvRecord>(new FileReader(cvsFile))
                    .withType(AccountCsvRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .build().parse();
            return accountCsvRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

