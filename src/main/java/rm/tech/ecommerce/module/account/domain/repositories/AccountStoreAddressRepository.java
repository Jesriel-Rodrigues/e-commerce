package rm.tech.ecommerce.module.account.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rm.tech.ecommerce.module.ecommerce.domain.entities.store.StoreAddress;



public interface AccountStoreAddressRepository extends JpaRepository<StoreAddress, Long>{
    
}
