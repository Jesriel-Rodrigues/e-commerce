package rm.tech.ecommerce.module.ecommerce.domain.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfigService;

import java.util.List;

public interface ProductAddonOptionsConfigServiceRepository extends JpaRepository<ProductAddonOptionsConfigService, Long> {

    List<ProductAddonOptionsConfigService> findByProductAddonOptionsConfigId(Long productAddonOptionsConfigId);

    List<ProductAddonOptionsConfigService> findByProductAddonOptionsConfigIdIn(List<Long> productAddonOptionsConfigIds);
}
