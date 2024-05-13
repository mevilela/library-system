package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.dto.library.RackDto;
import zely.project.librarysystem.mapper.RackMapper;
import zely.project.librarysystem.repository.library.RackRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RackServiceImpl implements RackService {

    private final RackRepository rackRepository;
    private final RackMapper rackMapper;

    public RackServiceImpl(RackRepository rackRepository, RackMapper rackMapper) {
        this.rackRepository = rackRepository;
        this.rackMapper = rackMapper;
    }

    @Override
    public List<RackDto> getAllRacks() {

        List<Rack> rackList = rackRepository.findAll();

        return rackList.stream()
                .map(rackMapper::toRackDto)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<RackDto> getRackById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<RackDto> getRackByLibrary(LibraryDto libraryDto) {
        return Optional.empty();
    }

    @Override
    public Optional<RackDto> getRackBySection(String section) {
        return Optional.empty();
    }

    @Override
    public RackDto createNewRack(RackDto rackDto) {
        return null;
    }

    @Override
    public Optional<RackDto> updateRackbyId(Integer id, RackDto rackDto) {
        return Optional.empty();
    }

    @Override
    public void deleteRackById(Integer id) {

    }
}
