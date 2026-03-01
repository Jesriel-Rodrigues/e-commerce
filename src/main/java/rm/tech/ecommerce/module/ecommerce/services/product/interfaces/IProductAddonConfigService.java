package rm.tech.ecommerce.module.ecommerce.services.product.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductAddonConfigRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductAddonConfigResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonConfig;

import java.util.List;
import java.util.Optional;

public interface IProductAddonConfigService {

    ProductAddonConfigResponse create(ProductAddonConfigRequest request);

    ProductAddonConfigResponse update(Long id, ProductAddonConfigRequest request);

    Optional<ProductAddonConfigResponse> findById(Long id);

    List<ProductAddonConfigResponse> findByProductId(Long productId);

    void deleteById(Long id);

    ProductAddonConfig findEntityByIdOrThrow(Long id);
}
