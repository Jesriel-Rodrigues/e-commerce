package rm.tech.ecommerce.module.ecommerce.services.product.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;

import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductSkuResponse;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    Optional<ProductResponse> findById(Long id);

    List<ProductResponse> findAll();

    List<ProductSkuResponse> findSkusByProductId(Long productId);

    void deleteById(Long id);

    Product findEntityByIdOrThrow(Long id);
}
