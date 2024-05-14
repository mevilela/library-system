package zely.project.librarysystem.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.library.LibraryCode;
import zely.project.librarysystem.domain.library.Rack;

import java.util.List;
import java.util.Optional;

public interface RackRepository extends JpaRepository<Rack, Integer> {

    List<Rack> findRackByLibraryCode(LibraryCode libraryCode);
}
