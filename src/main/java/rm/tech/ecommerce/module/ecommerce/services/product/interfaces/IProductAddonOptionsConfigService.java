package rm.tech.ecommerce.module.ecommerce.services.product.interfaces;

import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfig;

public interface IProductAddonOptionsConfigService {

    ProductAddonOptionsConfig findEntityByIdOrThrow(Long id);
}
