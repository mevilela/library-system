package zely.project.librarysystem.service.card;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.CardCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CardCsvServiceImpl implements CardCsvService {
    @Override
    public List<CardCsvRecord> convertCSV(File cvsFile) {

        try {
            List<CardCsvRecord> cardCsvRecords = new CsvToBeanBuilder<CardCsvRecord>(new FileReader(cvsFile))
                    .withType(CardCsvRecord.class)
                    .build()
                    .parse();
            return cardCsvRecords;
        }  catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
