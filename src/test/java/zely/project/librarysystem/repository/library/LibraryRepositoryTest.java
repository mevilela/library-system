package zely.project.librarysystem.repository.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import zely.project.librarysystem.bootstrap.BootstrapData;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.service.account.AccountCsvServiceImpl;
import zely.project.librarysystem.service.library.LibraryCsvServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({BootstrapData.class, LibraryCsvServiceImpl.class, AccountCsvServiceImpl.class})
class LibraryRepositoryTest {

    @Autowired
    LibraryRepository libraryRepository;

    @Test
    void testGetLibraryByName() {
        Page<Library> list = libraryRepository.findAllByNameIsLikeIgnoreCase("%central%", null);

        assertThat(list.getContent().size()).isEqualTo(1);
    }

}