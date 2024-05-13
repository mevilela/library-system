package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.dto.library.RackDto;

import java.util.List;
import java.util.Optional;

@Service
public interface RackService {

    List<RackDto> getAllRacks();

    Optional<RackDto> getRackById(Integer id);

    Optional<RackDto> getRackByLibrary(LibraryDto libraryDto);

    Optional<RackDto> getRackBySection(String section);

    RackDto createNewRack(RackDto rackDto);

    Optional<RackDto> updateRackbyId(Integer id, RackDto rackDto);

    void deleteRackById(Integer id);


}


