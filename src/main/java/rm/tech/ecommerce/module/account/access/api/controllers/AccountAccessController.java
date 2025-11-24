package rm.tech.ecommerce.module.account.access.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.account.access.api.dtos.request.AccountRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.request.LoginRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.response.AccountCreatedResponse;
import rm.tech.ecommerce.module.account.access.api.dtos.response.LoginResponse;
import rm.tech.ecommerce.module.account.access.services.interfaces.IAccountAccessService;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/accounts/access")
public class AccountAccessController {

    private final IAccountAccessService accountService;
    
    @PostMapping("/login")
    @Operation(security = {})
    public ResponseEntity<LoginResponse> loginAccount(@Valid @RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(accountService.accountLogin(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(security = {})
    public ResponseEntity<AccountCreatedResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }
}
