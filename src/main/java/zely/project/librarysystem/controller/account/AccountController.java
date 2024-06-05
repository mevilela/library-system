package zely.project.librarysystem.controller.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.domain.account.AccountType;
import zely.project.librarysystem.dto.account.AccountCreateRequestDto;
import zely.project.librarysystem.dto.account.AccountDto;
import zely.project.librarysystem.service.account.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDto> getAllAccounts(@RequestParam(required = false) String name,
                                           @RequestParam(required = false)AccountType type){

        return accountService.getAllAccounts(name, type);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(@PathVariable Integer id){
        return accountService.getAccountById(id).orElseThrow(NotFoundExceptionHandler::new);
    }

    @PostMapping
    public ResponseEntity<AccountCreateRequestDto> createNewAccount(@RequestBody AccountCreateRequestDto accountCreateRequestDto){
        AccountCreateRequestDto savedAccount = accountService.createNewAccount(accountCreateRequestDto);

        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateByAccountId(@PathVariable Integer id, @RequestBody AccountDto accountDto){
        accountService.updateAccountById(id, accountDto).orElseThrow(NotFoundExceptionHandler::new);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccountById(@PathVariable Integer id){
        if(!accountService.deleteAccountById(id)){
            throw new NotFoundExceptionHandler();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
