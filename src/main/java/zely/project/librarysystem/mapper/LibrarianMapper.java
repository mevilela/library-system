package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.account.Librarian;
import zely.project.librarysystem.dto.account.LibrarianDto;


@Mapper(uses = PersonMapper.class)
public interface LibrarianMapper {

    @Mapping(source = "person", target = "person")
    LibrarianDto toLibrarianDto(Librarian librarian);

    @Mapping(source = "person", target = "person")
    Librarian toLibrarianEntity(LibrarianDto librarianDto);

}