package rm.tech.ecommerce.config.RolesAndAdmin;

import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.account.domain.entities.AccountRole;
import rm.tech.ecommerce.module.account.domain.enums.TypeRole;
import rm.tech.ecommerce.module.account.domain.repositories.AccountRepository;
import rm.tech.ecommerce.module.account.domain.repositories.AccountRoleRepository;

@Configuration
public class RolesAndAdminConfig implements CommandLineRunner{
    
    private final AccountRoleRepository accountRoleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public RolesAndAdminConfig(AccountRoleRepository accountRoleRepository,
                           AccountRepository accountRepository,
                           PasswordEncoder passwordEncoder) {
        this.accountRoleRepository = accountRoleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        generateTypeRoles();

        Optional<AccountRole> roleAdminOpt = accountRoleRepository.findByTypeRole(TypeRole.ADMIN_SYSTEM);
        AccountRole roleAdmin = roleAdminOpt.isPresent() ? roleAdminOpt.get() : null;

        Optional<Account> userAdmin = accountRepository.findByEmail("admin@email.com");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    var user = new Account();
                    user.setEmail("admin@email.com");
                    user.setUserName("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    accountRepository.save(user);
                }
        );
    }

    private void generateTypeRoles(){

        for (TypeRole roleValue : TypeRole.values()) {
            
            Optional<AccountRole> role = accountRoleRepository.findByTypeRole(roleValue);

            if (!role.isPresent()) {
                
                AccountRole newRole = AccountRole.builder()
                    .typeRole(roleValue)
                .build();
                accountRoleRepository.save(newRole);
            }
        }
    }
}
