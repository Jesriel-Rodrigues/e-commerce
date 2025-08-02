 package rm.tech.ecommerce.module.ecommerce.domain.repositories.product;

 import org.springframework.data.jpa.repository.JpaRepository;
 import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;

 public interface ProductRepository extends JpaRepository<Product, Long> {
    
 }
