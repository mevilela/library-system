package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.account.Member;
import zely.project.librarysystem.dto.account.MemberDto;

@Mapper(uses = PersonMapper.class)
public interface MemberMapper {

    @Mapping(source = "person", target = "person")
    Member toMemberEntity(MemberDto memberDto);

    @Mapping(source = "person", target = "person")
    MemberDto toMemberDto(Member member);
}
