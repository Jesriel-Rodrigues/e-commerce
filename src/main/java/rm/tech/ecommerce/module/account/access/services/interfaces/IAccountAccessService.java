package rm.tech.ecommerce.module.account.access.services.interfaces;

import rm.tech.ecommerce.module.account.access.api.dtos.request.AccountRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.request.LoginRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.response.AccountCreatedResponse;
import rm.tech.ecommerce.module.account.access.api.dtos.response.LoginResponse;

public interface IAccountAccessService {
    
    LoginResponse accountLogin(LoginRequest request);

    AccountCreatedResponse createAccount( AccountRequest request);
}
