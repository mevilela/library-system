package zely.project.librarysystem.service.book.csv;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.AuthorCsvRecord;

import java.io.File;
import java.util.List;

@Service
public interface AuthorCsvService {
    List<AuthorCsvRecord> convertCSV(File cvsFile);
}
