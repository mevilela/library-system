package zely.project.librarysystem.repository.library;
import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.library.Library;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

    List<Library> findAllByNameIsLikeIgnoreCase(String libraryName);
}
