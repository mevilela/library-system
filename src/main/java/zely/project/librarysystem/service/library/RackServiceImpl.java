package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.library.Rack;
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

        return Optional.ofNullable(rackMapper.toRackDto(rackRepository.findById(id).orElse(null)));
    }

//    @Override
//    public List<RackDto> getRackByLibraryCode(LibraryCode libraryCode) {
//
//        List<Rack> racks = rackRepository.findRackByLibraryCode(libraryCode);
//        List<RackDto> rackDtos = new ArrayList<>();
//
//        for (Rack rack : racks){
//            rackDtos.add(rackMapper.toRackDto(rack));
//        }
//
//        return rackDtos;
//
//    }

    @Override
    public Optional<RackDto> getRackBySection(String section) {
        return Optional.empty();
    }

    @Override
    public RackDto createNewRack(RackDto rackDto) {

        return rackMapper.toRackDto(rackRepository.save(rackMapper.toRackEntity(rackDto)));

    }

    @Override
    public Optional<RackDto> updateRackbyId(Integer id, RackDto rackDto) {

       return rackRepository.findById(id).map(
                foundRack -> {
                    foundRack.setRackNumber(rackDto.getRackNumber());
                    foundRack.setLocation(rackDto.getLocation());
                    foundRack.setSection(rackDto.getSection());

                    return foundRack;
                }).map(rackRepository::save)
                .map(rackMapper::toRackDto);
    }

    @Override
    public boolean deleteRackById(Integer id) {

        if(rackRepository.existsById(id)){
            rackRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
