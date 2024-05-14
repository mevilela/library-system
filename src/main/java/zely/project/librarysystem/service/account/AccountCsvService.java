package zely.project.librarysystem.service.account;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.csv.AccountCsvRecord;

import java.io.File;
import java.util.List;

@Service
public interface AccountCsvService {

    List<AccountCsvRecord> convertCSV(File cvsFile);

}
