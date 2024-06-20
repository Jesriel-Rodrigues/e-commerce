package rm.tech.ecommerce.module.account.services.interfaces;

import java.util.Optional;

import rm.tech.ecommerce.module.account.access.api.dtos.request.AccountRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.response.AccountCreatedResponse;
import rm.tech.ecommerce.module.account.domain.entities.Account;

public interface IAccountService {

    Optional<Account> findById( Long id);

    Account findByIdWithThrow( Long id);
    
    Optional<Account> findByEmail(String email);

    boolean emailExists(String email);

    Account findByEmailWithThrow(String email);

    AccountCreatedResponse create (AccountRequest request);
}
