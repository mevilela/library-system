package zely.project.librarysystem.service.book.csv;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.BookItemCsvRecord;

import java.io.File;
import java.util.List;

@Service
public interface BookItemCsvService {

    List<BookItemCsvRecord> convertCSV(File cvsFile);
}
