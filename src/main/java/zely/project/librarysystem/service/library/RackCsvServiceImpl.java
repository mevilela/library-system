package zely.project.librarysystem.service.library;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.RackCsvRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class RackCsvServiceImpl implements RackCsvService {
    @Override
    public List<RackCsvRecord> convertCSV(File cvsFile) {
        try{
            List<RackCsvRecord> rackCsvRecords = new CsvToBeanBuilder<RackCsvRecord>(new FileReader(cvsFile))
                    .withType(RackCsvRecord.class)
                    .build()
                    .parse();
            return rackCsvRecords;
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}

