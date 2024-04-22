package rm.tech.ecommerce.module.account.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rm.tech.ecommerce.module.account.domain.entities.Account;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long>{
    
    Optional<Account> findByEmail(String email);
}
