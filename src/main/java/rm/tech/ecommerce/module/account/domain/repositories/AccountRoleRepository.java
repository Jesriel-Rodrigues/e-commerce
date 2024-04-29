package rm.tech.ecommerce.module.account.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rm.tech.ecommerce.module.account.domain.entities.AccountRole;
import java.util.Optional;

import rm.tech.ecommerce.module.account.domain.enums.TypeRole;


public interface AccountRoleRepository extends JpaRepository<AccountRole, Long>{
    
    Optional<AccountRole> findByTypeRole(TypeRole typeRole);
}
