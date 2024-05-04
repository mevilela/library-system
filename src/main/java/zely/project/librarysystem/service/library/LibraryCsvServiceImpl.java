package zely.project.librarysystem.service.library;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.LibraryCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
@Service
public class LibraryCsvServiceImpl implements LibraryCsvService {
    @Override
    public List<LibraryCSVRecord> convertCSV(File cvsFile) {

        try {
            List<LibraryCSVRecord> libraryCSVRecords = new CsvToBeanBuilder<LibraryCSVRecord>(new FileReader(cvsFile))
                    .withType(LibraryCSVRecord.class)
                    .build().parse();
            return libraryCSVRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
