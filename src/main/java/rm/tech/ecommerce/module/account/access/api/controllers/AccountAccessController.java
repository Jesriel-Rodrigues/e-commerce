package rm.tech.ecommerce.module.account.access.api.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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
import rm.tech.ecommerce.module.account.access.api.dtos.response.LoginResponse;
import rm.tech.ecommerce.module.account.access.services.interfaces.IAccountAccessService;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/accounts/access")
public class AccountAccessController {

    private final IAccountAccessService accountService;
    
    @MutationMapping()
    @PostMapping()
    public ResponseEntity<LoginResponse> loginAccount( @Argument LoginRequest loginRequest){

        return new ResponseEntity<>(accountService.accountLogin(loginRequest), HttpStatus.OK);
    }

    // @SchemaMapping()
    @MutationMapping()
    @PostMapping("/create")
    public ResponseEntity<AccountCreatedResponse> createAccount(@Argument AccountRequest accountRequest) {
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }
    
}
