package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.account.Librarian;
import zely.project.librarysystem.dto.account.LibrarianDto;


@Mapper(componentModel = "spring")
public interface LibrarianMapper {

    LibrarianDto toLibrarianDto(Librarian librarian);

    Librarian toLibrarianEntity(LibrarianDto librarianDto);

}