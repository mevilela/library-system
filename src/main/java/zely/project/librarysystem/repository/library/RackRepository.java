package zely.project.librarysystem.repository.library;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.library.Rack;

public interface RackRepository extends JpaRepository<Rack, Integer> {

//    List<Rack> findRackByLibraryCode(LibraryCode libraryCode);
}
