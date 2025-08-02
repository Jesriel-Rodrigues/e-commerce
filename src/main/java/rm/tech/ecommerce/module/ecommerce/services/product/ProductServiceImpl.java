package rm.tech.ecommerce.module.ecommerce.services.product;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.api.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.Structure;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductRepository;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.structure.StructureRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductService;
import rm.tech.ecommerce.module.ecommerce.services.structure.interfaces.IStructureService;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;
    private final StructureRepository structureRepository;

    private final IStructureService structureService;

    @Override
    public Product create(ProductRequest request){

        Structure structure = structureService.getById(request.getStructureId());

        Product product = new Product(
                request.getName(),
                request.getDescription(),
                null,
                null,
                structure);

        return productRepository.save(product);
    }
}
