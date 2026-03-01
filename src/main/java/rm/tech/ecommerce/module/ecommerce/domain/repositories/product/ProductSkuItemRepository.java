package rm.tech.ecommerce.module.ecommerce.domain.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSkuItem;

import java.util.List;

public interface ProductSkuItemRepository extends JpaRepository<ProductSkuItem, Long> {

    List<ProductSkuItem> findByProductSkuId(Long productSkuId);

    void deleteByProductSkuId(Long productSkuId);
}
