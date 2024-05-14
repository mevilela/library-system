package zely.project.librarysystem.service.card;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.CardCsvRecord;

import java.io.File;
import java.util.List;

@Service
public interface CardCsvService {
    List<CardCsvRecord> convertCSV(File cvsFile);
}
