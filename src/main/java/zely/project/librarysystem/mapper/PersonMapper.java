package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.account.Person;
import zely.project.librarysystem.dto.account.PersonDto;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toPersonEntity(PersonDto personDto);

    PersonDto toPersonDto(Person person);
}
