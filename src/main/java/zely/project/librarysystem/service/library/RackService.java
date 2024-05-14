package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.library.LibraryCode;
import zely.project.librarysystem.dto.library.RackDto;

import java.util.List;
import java.util.Optional;

@Service
public interface RackService {

    List<RackDto> getAllRacks();

    Optional<RackDto> getRackById(Integer id);

    List<RackDto> getRackByLibraryCode(LibraryCode libraryCode);

    Optional<RackDto> getRackBySection(String section);

    RackDto createNewRack(RackDto rackDto);

    Optional<RackDto> updateRackbyId(Integer id, RackDto rackDto);

    boolean deleteRackById(Integer id);


}


