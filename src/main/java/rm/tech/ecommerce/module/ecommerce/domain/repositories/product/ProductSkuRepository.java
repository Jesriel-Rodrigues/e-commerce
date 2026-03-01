package rm.tech.ecommerce.module.ecommerce.domain.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSku;

import java.util.List;

public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {

    List<ProductSku> findByProductId(Long productId);
}
