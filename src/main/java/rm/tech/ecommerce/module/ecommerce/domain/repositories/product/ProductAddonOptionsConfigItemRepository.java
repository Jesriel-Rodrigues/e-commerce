package rm.tech.ecommerce.module.ecommerce.domain.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfigItem;

import java.util.List;

public interface ProductAddonOptionsConfigItemRepository extends JpaRepository<ProductAddonOptionsConfigItem, Long> {

    List<ProductAddonOptionsConfigItem> findByProductAddonOptionsConfigId(Long productAddonOptionsConfigId);

    List<ProductAddonOptionsConfigItem> findByProductAddonOptionsConfigIdIn(List<Long> productAddonOptionsConfigIds);
}
