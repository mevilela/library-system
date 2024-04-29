package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import zely.project.librarysystem.domain.account.Member;
import zely.project.librarysystem.dto.account.MemberDto;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toMemberEntity(MemberDto memberDto);

    MemberDto toMemberDto(Member member);
}
