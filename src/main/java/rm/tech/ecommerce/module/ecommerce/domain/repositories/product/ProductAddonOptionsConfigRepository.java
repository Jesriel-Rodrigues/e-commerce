package rm.tech.ecommerce.module.ecommerce.domain.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfig;

import java.util.List;

public interface ProductAddonOptionsConfigRepository extends JpaRepository<ProductAddonOptionsConfig, Long> {

    List<ProductAddonOptionsConfig> findByProductAddonConfigId(Long productAddonConfigId);
}

