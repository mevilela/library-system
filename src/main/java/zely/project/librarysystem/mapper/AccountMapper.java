package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.account.*;
import zely.project.librarysystem.dto.account.AccountCreateRequestDto;
import zely.project.librarysystem.dto.account.AccountDto;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, LibrarianMapper.class})
public interface AccountMapper {

    @Mapping(target = "accountType", source = "accountType")
    AccountDto toAccountDto(Account account);

    @Mapping(target = "accountType", source = "accountType")
    @Mapping(target = "accountStatus", source = "accountStatus")
    AccountCreateRequestDto toAccountCreateDto(Account account);

    @Mapping(target = "accountType", source = "accountType")
    @Mapping(target = "accountStatus", source = "accountStatus")
    default Account accountFromCreateDto(AccountCreateRequestDto accountCreateRequestDto) {

        if (accountCreateRequestDto.getAccountType() == AccountType.MEMBER) {
            return new Member();
        } else if (accountCreateRequestDto.getAccountType() == AccountType.LIBRARIAN) {
            return new Librarian();
        } else {
            throw new IllegalArgumentException("unkown account type");
        }

    }


    // Mapeamento para um tipo concreto (como Member ou Librarian)
    @Mapping(target = "accountType", source = "accountType")
    default Account toAccountEntity(AccountDto accountDto) {
        if (accountDto.getAccountType() == AccountType.MEMBER) {
            return new Member();  //
        } else if (accountDto.getAccountType() == AccountType.LIBRARIAN) {
            return new Librarian();
        } else {
            throw new IllegalArgumentException("unkown account type");
        }
    }


}
