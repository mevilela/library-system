package zely.project.librarysystem.service.book.csv;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.PublisherCsvRecord;

import java.io.File;
import java.util.List;

@Service
public interface PublisherCsvService {

    List<PublisherCsvRecord> convertCSV(File cvsFile);
}
