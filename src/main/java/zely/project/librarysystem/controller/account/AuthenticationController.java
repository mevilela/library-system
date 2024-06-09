package zely.project.librarysystem.controller.account;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.project.librarysystem.config.security.TokenService;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.dto.account.AuthenticationDto;
import zely.project.librarysystem.dto.account.LoginResponseDto;
import zely.project.librarysystem.service.account.AccountService;
import zely.project.librarysystem.service.account.AuthorizationService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;

    private final AccountService accountService;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, AuthorizationService authorizationService, AccountService accountService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
        this.accountService = accountService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Account) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));


    }
}
