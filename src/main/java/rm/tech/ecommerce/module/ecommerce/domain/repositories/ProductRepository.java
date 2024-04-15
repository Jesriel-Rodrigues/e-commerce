package rm.tech.ecommerce.module.ecommerce.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import rm.tech.ecommerce.module.ecommerce.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
