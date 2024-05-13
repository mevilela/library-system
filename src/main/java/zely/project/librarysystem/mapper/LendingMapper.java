package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.booking.Lending;
import zely.project.librarysystem.dto.booking.LendingDto;

@Mapper(componentModel = "spring", uses = {AccountMapper.class, MemberMapper.class, LibrarianMapper.class})
public interface LendingMapper {

    LendingDto toLendingDto(Lending lending);

    Lending toLendingEntity(LendingDto lendingDto);
}
