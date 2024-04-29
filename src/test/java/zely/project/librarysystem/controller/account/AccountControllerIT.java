package zely.project.librarysystem.controller.account;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.webjars.NotFoundException;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.account.AccountStatus;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.domain.account.Person;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.dto.account.AccountDto;
import zely.project.librarysystem.dto.account.PersonDto;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.mapper.AccountMapper;
import zely.project.librarysystem.mapper.MemberMapper;
import zely.project.librarysystem.mapper.PersonMapper;
import zely.project.librarysystem.repository.account.AccountRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AccountControllerIT {

    @Autowired
    AccountController accountController;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MemberMapper memberMapper;
    @Autowired
    PersonMapper personMapper;
    @Autowired
    AccountMapper accountMapper;

    @Test
    void getAllAccounts(){
        List<AccountDto> accountDtos = (List<AccountDto>) accountController.getAllAccounts();

        assertThat(accountDtos.size()).isEqualTo(2);
    }

    @Test
    void testGetById() {
        Account account = accountRepository.findAll().get(0);

        AccountDto accountDto = accountController.getAccountById(account.getId());

        assertThat(accountDto).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void testEmptyList(){
        accountRepository.deleteAll();
        List<AccountDto> accountDtos = accountController.getAllAccounts();

        assertThat(accountDtos.size()).isEqualTo(0);
    }

    @Transactional
    @Rollback
    @Test
    void createNewAccountTest(){

        PersonDto joao = new PersonDto();
        joao.setName("joao");
        joao.setAddress("Rua Paissandu 344");
        joao.setEmail("joao@gmail.com");
        joao.setPhone("222111333");

        AccountDto accountDto = new AccountDto();
        accountDto.setAccountStatus(AccountStatus.ACTIVE);
        accountDto.setAccountType(AccountType.LIBRARIAN);
        accountDto.setPerson(joao);

        ResponseEntity responseEntity = accountController.createNewAccount(accountDto);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        AccountDto createdAccount = (AccountDto) responseEntity.getBody();
        assertThat(createdAccount).isNotNull();

    }

    @Transactional
    @Rollback
    @Test
    void updateExistingAccount(){

        Account account = accountRepository.findAll().get(0);
        AccountDto accountDto = accountMapper.toAccountDto(account);

        PersonDto joanaDto = new PersonDto();
        joanaDto.setName("joana");

        accountDto.setPerson(joanaDto);

        ResponseEntity responseEntity = accountController.updateByAccountId(account.getId(), accountDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Account updatedAccount = accountRepository.findById(account.getId()).get();

        assertThat(updatedAccount).isNotNull();
        assertThat(updatedAccount.getPerson().getName()).isEqualTo("joana");
    }

    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundException.class, () ->
                accountController.updateByAccountId(2222, null));
    }

    @Transactional
    @Rollback
    @Test
    void deleteByIdFound(){
        Account account = accountRepository.findAll().get(0);

        ResponseEntity responseEntity = accountController.deleteAccountById(account.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(accountRepository.findById(account.getId()).isEmpty());


    }

    @Test
    void testDeleteNotFound(){
        assertThrows(NotFoundException.class, () ->
                accountController.deleteAccountById(22));
    }

}


