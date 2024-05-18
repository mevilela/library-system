package zely.project.librarysystem.service.book.csv;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.BookCsvRecord;

import java.io.File;
import java.util.List;

@Service
public interface BookCsvService {
    List<BookCsvRecord> convertCSV(File cvsFile);
}
