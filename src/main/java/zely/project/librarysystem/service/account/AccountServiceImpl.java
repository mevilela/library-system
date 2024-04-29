package zely.project.librarysystem.service.account;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.dto.account.AccountDto;
import zely.project.librarysystem.mapper.AccountMapper;
import zely.project.librarysystem.mapper.PersonMapper;
import zely.project.librarysystem.repository.account.AccountRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PersonMapper personMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, PersonMapper personMapper){
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.personMapper = personMapper;
    }


    @Override
    public List<AccountDto> getAllAccounts() {

        return accountRepository.findAll().stream().map(accountMapper::toAccountDto).collect(Collectors.toList());

    }

    @Override
    public List<Account> getAllMembers() {
        return null;
    }

    @Override
    public List<Account> getAllLibrarians() {
        return null;
    }

    @Override
    public Optional<AccountDto> getAccountById(Integer id) {
        return Optional.ofNullable(accountMapper.toAccountDto(accountRepository.findById(id).orElse(null)));
    }

    @Override
    public AccountDto createNewAccount(AccountDto accountDto) {

        return accountMapper.toAccountDto(accountRepository.save((accountMapper.toAccountEntity(accountDto))));
    }

    @Override
    public Optional<AccountDto> updateAccountById(Integer id, AccountDto accountDto) {
        return accountRepository.findById(id).map(
                foundAccount -> {

                    foundAccount.setPerson(personMapper.toPersonEntity(accountDto.getPerson()));
                    foundAccount.setAccountStatus(accountDto.getAccountStatus());
                    foundAccount.setAccountType(accountDto.getAccountType());

                    return foundAccount;
                }).map(accountRepository::save)
                .map(accountMapper::toAccountDto);
    }

    @Override
    public boolean deleteAccountById(Integer id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

