package rm.tech.ecommerce.module.account.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rm.tech.ecommerce.module.account.domain.entities.AccountAddress;


public interface AccountAddressRepository extends JpaRepository<AccountAddress, Long>{
    
}
