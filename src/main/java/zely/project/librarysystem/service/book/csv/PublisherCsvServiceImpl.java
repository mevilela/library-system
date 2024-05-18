package zely.project.librarysystem.service.book.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.PublisherCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class PublisherCsvServiceImpl implements PublisherCsvService {
    @Override
    public List<PublisherCsvRecord> convertCSV(File cvsFile) {
        try {
            List<PublisherCsvRecord> publisherCsvRecords = new CsvToBeanBuilder<PublisherCsvRecord>(new FileReader(cvsFile))
                    .withType(PublisherCsvRecord.class)
                    .build().parse();
            return publisherCsvRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
