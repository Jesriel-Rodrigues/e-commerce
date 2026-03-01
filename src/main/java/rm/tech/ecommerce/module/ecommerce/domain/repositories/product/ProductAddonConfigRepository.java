package rm.tech.ecommerce.module.ecommerce.domain.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonConfig;

import java.util.List;

public interface ProductAddonConfigRepository extends JpaRepository<ProductAddonConfig, Long> {

    List<ProductAddonConfig> findByProductId(Long productId);
}
