package zely.project.librarysystem.controller.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
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
    public List<AccountDto> getAllAccounts(@RequestParam(required = false) String name){

        return accountService.getAllAccounts(name);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountById(Integer id){
        return accountService.getAccountById(id).orElseThrow(() -> new NotFoundException("id not found"));
    }

    @PostMapping
    public ResponseEntity<AccountDto> createNewAccount(@RequestBody AccountDto accountDto){
        AccountDto savedAccount = accountService.createNewAccount(accountDto);

        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateByAccountId(@PathVariable Integer id, @RequestBody AccountDto accountDto){
        accountService.updateAccountById(id, accountDto).orElseThrow(() -> new NotFoundException("account not found"));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccountById(@PathVariable Integer id){
        if(!accountService.deleteAccountById(id)){
            throw new NotFoundException("account not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
