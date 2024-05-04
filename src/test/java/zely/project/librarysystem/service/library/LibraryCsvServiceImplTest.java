package zely.project.librarysystem.service.library;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;
import zely.project.librarysystem.csv.LibraryCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LibraryCsvServiceImplTest {
    LibraryCsvService libraryCsvService = new LibraryCsvServiceImpl();

    @Test
    void convertCSV() throws FileNotFoundException{

        File file = ResourceUtils.getFile("classpath:csvdata/library-data.csv");

        List<LibraryCSVRecord> recs = libraryCsvService.convertCSV(file);

        System.out.println(recs.size());

        assertThat(recs.size()).isGreaterThan(0);
    }
}
