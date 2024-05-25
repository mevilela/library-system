package zely.project.librarysystem.service.account;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountStatus;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.dto.account.AccountCreateRequestDto;
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
    public List<AccountDto> getAllAccounts(String name, AccountType type) {

        List<Account> accountList;

        if(StringUtils.hasText(name) && type == null){
            accountList = accountListByName(name);
        } else if (!StringUtils.hasText(name) && type != null) {
            accountList = accountListByType(type);

        } else if (StringUtils.hasText(name) && type != null) {
            accountList = accountListByNameAndType(name, type);

        } else {
            accountList = accountRepository.findAll();
        }

        return accountList.stream()
                .map(accountMapper::toAccountDto)
                .collect(Collectors.toList());
    }

    private List<Account> accountListByNameAndType(String name, AccountType type) {
        return accountRepository.findAccountByPersonNameIsLikeIgnoreCaseAndAccountType(name, type);
    }

    private List<Account> accountListByType(AccountType type) {
        return accountRepository.findAllByAccountType(type);
    }

    private List<Account> accountListByName(java.lang.String name) {

        return accountRepository.findAccountByPersonNameIsLikeIgnoreCase("%" + name + "%");
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
    public AccountCreateRequestDto createNewAccount(AccountCreateRequestDto accountCreateRequestDto) {

        Account account = accountMapper.accountFromCreateDto(accountCreateRequestDto);

        String accountStatus = accountCreateRequestDto.getAccountStatus().toString();


        account.setPassword(accountCreateRequestDto.getPassword());
        account.setPerson(personMapper.toPersonEntity(accountCreateRequestDto.getPerson()));
        account.setAccountStatus(AccountStatus.valueOf(accountStatus));


        return accountMapper.toAccountCreateDto(accountRepository.save(account));


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

