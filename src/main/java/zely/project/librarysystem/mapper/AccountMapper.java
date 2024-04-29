package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.domain.account.Librarian;
import zely.project.librarysystem.domain.account.Member;
import zely.project.librarysystem.dto.account.AccountDto;

@Mapper(componentModel = "spring", uses = {MemberMapper.class, LibrarianMapper.class})
public interface AccountMapper {

    @Mapping(target = "accountType", source = "accountType")
    AccountDto toAccountDto(Account account);

    // Mapeamento para um tipo concreto (como Member ou Librarian)

    @Mapping(target = "accountType", source = "accountType")
    default Account toAccountEntity(AccountDto accountDto) {
        if (accountDto.getAccountType() == AccountType.MEMBER) {
            return new Member();  //
        } else if (accountDto.getAccountType() == AccountType.LIBRARIAN) {
            return new Librarian();
        } else {
            throw new IllegalArgumentException("Tipo de conta desconhecido");
        }
    }
}
