package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.account.Person;
import zely.project.librarysystem.dto.account.PersonDto;

@Mapper
public interface PersonMapper {

    Person personToPersonDto(PersonDto personDto);

    PersonDto personDtoToPerson(Person person);
}
