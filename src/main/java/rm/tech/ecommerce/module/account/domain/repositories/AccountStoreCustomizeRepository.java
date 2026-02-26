package rm.tech.ecommerce.module.account.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rm.tech.ecommerce.module.ecommerce.domain.entities.store.StoreCustomize;


public interface AccountStoreCustomizeRepository extends JpaRepository<StoreCustomize, Long>{
    
}
