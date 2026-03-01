package rm.tech.ecommerce.module.ecommerce.services.product.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductItemRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductItemResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;

import java.util.List;
import java.util.Optional;

public interface IProductItemService {

    ProductItemResponse create(ProductItemRequest request);

    ProductItemResponse update(Long id, ProductItemRequest request);

    Optional<ProductItemResponse> findById(Long id);

    List<ProductItemResponse> findAll();

    void deleteById(Long id);

    ProductItem findEntityByIdOrThrow(Long id);
}
