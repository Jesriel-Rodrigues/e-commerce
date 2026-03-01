package rm.tech.ecommerce.module.ecommerce.services.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfig;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductAddonOptionsConfigRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductAddonOptionsConfigService;

@Service
@AllArgsConstructor
public class ProductAddonOptionsConfigServiceImpl implements IProductAddonOptionsConfigService {

    private final ProductAddonOptionsConfigRepository repository;

    @Override
    public ProductAddonOptionsConfig findEntityByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opção de addon não encontrada: " + id));
    }
}
