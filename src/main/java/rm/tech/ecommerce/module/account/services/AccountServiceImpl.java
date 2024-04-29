package rm.tech.ecommerce.module.account.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.account.access.api.dtos.request.AccountRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.response.AccountCreatedResponse;
import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.account.domain.repositories.AccountRepository;
import rm.tech.ecommerce.module.account.services.interfaces.IAccountService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{
    
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Account> findByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    @Override
    public boolean emailExists(String email){
        Optional<Account> account = findByEmail(email);

        return account.isPresent();
    }

    @Override
    public Account findByEmailWithThrow(String email){
        return findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Conta com este email não encontrada!"));
    }

    @Override
    public AccountCreatedResponse create(AccountRequest request) {
        
        Account account = Account.builder()
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
        .build();

        account = accountRepository.save(account);

        return new AccountCreatedResponse(account.getId(), account.getEmail(), null, null);
    }
}
