package zely.project.librarysystem.controller.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.service.account.AccountServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
