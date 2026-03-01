package rm.tech.ecommerce.module.ecommerce.services.product.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductSkuDefinitionRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductSkuResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;

import java.util.List;

public interface IProductSkuService {

    void syncSkusForProduct(Product product, List<ProductSkuDefinitionRequest> skuDefinitions);

    List<ProductSkuResponse> findByProductId(Long productId);

    void deleteByProductId(Long productId);
}
