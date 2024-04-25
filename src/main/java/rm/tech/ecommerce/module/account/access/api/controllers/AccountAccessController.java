package rm.tech.ecommerce.module.account.access.api.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.account.access.api.dtos.request.AccountRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.request.LoginRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.response.AccountCreatedResponse;
import rm.tech.ecommerce.module.account.access.api.dtos.response.LoginResponse;
import rm.tech.ecommerce.module.account.access.services.interfaces.IAccountAccessService;

@RestController
@AllArgsConstructor
public class AccountAccessController {

    private final IAccountAccessService accountService;
    
    @MutationMapping()
    public LoginResponse loginAccount( @Argument LoginRequest loginRequest){
        return accountService.accountLogin(loginRequest);
    }

    @MutationMapping()
    public AccountCreatedResponse createAccount(@Argument AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }
    
}
