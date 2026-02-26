package rm.tech.ecommerce.module.account.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rm.tech.ecommerce.module.ecommerce.domain.entities.store.StoreAccount;


public interface AccountStoreRepository extends JpaRepository<StoreAccount, Long>{
    
}
