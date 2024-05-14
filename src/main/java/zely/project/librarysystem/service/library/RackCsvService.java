package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.RackCsvRecord;

import java.io.File;
import java.util.List;

@Service
public interface RackCsvService {

    List<RackCsvRecord> convertCSV(File cvsFile);
}

