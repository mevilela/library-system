package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.booking.Lending;
import zely.project.librarysystem.dto.booking.LendingDto;

@Mapper(componentModel = "spring", uses = {BookItemMapper.class, AccountMapper.class, MemberMapper.class, LibrarianMapper.class})
public interface LendingMapper {

    @Mapping(source = "bookItem", target = "bookItem")
    @Mapping(source = "id", target = "id")
    LendingDto toLendingDto(Lending lending);
    @Mapping(source = "bookItem", target = "bookItem")
    @Mapping(source = "id", target = "id")
    Lending toLendingEntity(LendingDto lendingDto);



}
