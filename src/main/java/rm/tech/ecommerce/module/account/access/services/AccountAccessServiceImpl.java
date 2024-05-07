package rm.tech.ecommerce.module.account.access.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.exceptions.ResourceBadRequestException;
import rm.tech.ecommerce.module.account.access.api.dtos.request.AccountRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.request.LoginRequest;
import rm.tech.ecommerce.module.account.access.api.dtos.response.AccountCreatedResponse;
import rm.tech.ecommerce.module.account.access.api.dtos.response.LoginResponse;
import rm.tech.ecommerce.module.account.access.services.interfaces.IAccountAccessService;
import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.account.services.interfaces.IAccountRoleService;
import rm.tech.ecommerce.module.account.services.interfaces.IAccountService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class AccountAccessServiceImpl implements IAccountAccessService{
    
    private final PasswordEncoder passwordEncoder;
    private final IAccountService accountService;
    private final JwtEncoder encoder;
    private final IAccountRoleService roleService;

    @Override
    public LoginResponse accountLogin(LoginRequest request){

        Account account = accountService.findByEmailWithThrow(request.email());

        if (!isLoginCorrect(request.password(), account.getPassword())) {
            throw new ResourceBadRequestException("Senha invalida!");
        }

        Instant now = Instant.now();
        Long expiresIn = 5L;

        String rolesClaims = roleService.claimRolesAuthorityByAccount(account);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("rm-tech-ecommerce")
                .issuedAt(now)
                .subject(account.getEmail())
                .expiresAt(now.plus(expiresIn, ChronoUnit.DAYS))
                .claim("scope", rolesClaims)
            .build();
                
        String tokenJwt = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponse(tokenJwt, expiresIn);
    }

    @Override
    public AccountCreatedResponse createAccount( AccountRequest request){

        if (accountService.emailExists(request.email())) {
            throw new ResourceBadRequestException("Já possui conta criada com este email");
        }

        AccountCreatedResponse accResp = accountService.create(request);

        LoginResponse logResp = accountLogin(new LoginRequest(accResp.getEmail(), request.password()));

        accResp.setToken(logResp.token());
        accResp.setExpiresIn(logResp.expiresIn());
        return accResp;
    }

    public boolean isLoginCorrect( String requestPassword , String accountPassword){
        return passwordEncoder.matches(requestPassword, accountPassword);
    }
}
