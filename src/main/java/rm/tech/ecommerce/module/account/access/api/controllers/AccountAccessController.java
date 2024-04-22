package rm.tech.ecommerce.module.account.access.api.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.account.access.api.dtos.request.AccountRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.request.LoginRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.response.AccountCreatedResponse;
import rm.tech.ecommerce.module.account.access.services.interfaces.IAccountAccessService;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/accounts/access")
public class AccountAccessController {

    private final IAccountAccessService accountService;
    
    @PostMapping("/login")
    public ResponseEntity<Object> loginAccount( @RequestBody LoginRequest request){

        return new ResponseEntity<>(accountService.accountLogin(request), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AccountCreatedResponse> createAccount(@RequestBody AccountRequest request) {
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
    }
    
}
