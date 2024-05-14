package zely.project.librarysystem.repository.library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.library.Library;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

    Page<Library> findAllByNameIsLikeIgnoreCase(String libraryName, Pageable pageable);

}
