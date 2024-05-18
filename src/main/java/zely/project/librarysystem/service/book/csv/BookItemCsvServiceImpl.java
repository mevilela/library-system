package zely.project.librarysystem.service.book.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.BookItemCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class BookItemCsvServiceImpl implements BookItemCsvService {
    @Override
    public List<BookItemCsvRecord> convertCSV(File cvsFile) {
        try {
            List<BookItemCsvRecord> bookItemCsvRecords = new CsvToBeanBuilder<BookItemCsvRecord>(new FileReader(cvsFile))
                    .withType(BookItemCsvRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .build().parse();
            return bookItemCsvRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
