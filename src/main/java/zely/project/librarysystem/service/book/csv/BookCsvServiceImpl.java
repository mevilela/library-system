package zely.project.librarysystem.service.book.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.BookCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class BookCsvServiceImpl implements BookCsvService {
    @Override
    public List<BookCsvRecord> convertCSV(File cvsFile) {
        try {
            List<BookCsvRecord> bookCsvRecords = new CsvToBeanBuilder<BookCsvRecord>(new FileReader(cvsFile))
                    .withType(BookCsvRecord.class)
                    .build().parse();
            return bookCsvRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
