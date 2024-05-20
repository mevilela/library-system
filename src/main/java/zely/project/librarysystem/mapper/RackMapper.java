package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.dto.library.RackDto;

@Mapper(componentModel = "spring", uses={LibraryMapper.class})
public interface RackMapper {

    RackDto toRackDto(Rack rack);

    Rack toRackEntity(RackDto rackDto);

}
