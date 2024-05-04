package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.LibraryCSVRecord;

import java.io.File;
import java.util.List;

@Service
public interface LibraryCsvService {

    List<LibraryCSVRecord> convertCSV(File cvsFile);
}
