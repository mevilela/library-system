package zely.project.librarysystem.service.book.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.AuthorCsvRecord;
import zely.project.librarysystem.csv.LibraryCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class AuthorCsvServiceImpl implements AuthorCsvService {
    @Override
    public List<AuthorCsvRecord> convertCSV(File cvsFile) {
        try {
            List<AuthorCsvRecord> authorCsvRecords = new CsvToBeanBuilder<AuthorCsvRecord>(new FileReader(cvsFile))
                    .withType(AuthorCsvRecord.class)
                    .build().parse();
            return authorCsvRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
