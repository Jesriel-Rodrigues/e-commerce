package rm.tech.ecommerce.module.ecommerce.services.product.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;

public interface IProductService {

    Product create(ProductRequest request);
}
