package rm.tech.ecommerce.module.ecommerce.services.product.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductServiceCatalogRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductServiceCatalogResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductService;

import java.util.List;
import java.util.Optional;

public interface IProductServiceCatalogService {

    ProductServiceCatalogResponse create(ProductServiceCatalogRequest request);

    ProductServiceCatalogResponse update(Long id, ProductServiceCatalogRequest request);

    Optional<ProductServiceCatalogResponse> findById(Long id);

    List<ProductServiceCatalogResponse> findAll();

    void deleteById(Long id);

    ProductService findEntityByIdOrThrow(Long id);
}
