package rm.tech.ecommerce.module.account.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rm.tech.ecommerce.module.account.domain.entities.AccountStoreCustomize;


public interface AccountStoreCustomizeRepository extends JpaRepository<AccountStoreCustomize, Long>{
    
}
