package rm.tech.ecommerce.module.ecommerce.services.product;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.api.dto.request.ProductCustomizationFieldsRequest;
import rm.tech.ecommerce.module.ecommerce.api.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationFields;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.enums.CustomizationFieldsStatus;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.Structure;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductRepository;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.structure.StructureRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductService;
import rm.tech.ecommerce.module.ecommerce.services.structure.interfaces.IStructureService;

import java.util.List;

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


    public List<CustomizationFields> buildCustomizationFields(List<ProductCustomizationFieldsRequest> customizations){
        return customizations.stream()
                .map(request -> new CustomizationFields(
                        null,
                        request.getName(),
                        request.getCustomType(),
                        request.isNotNull(),
                        request.getSequence(),
                        CustomizationFieldsStatus.ENABLE,
                        null,
                        null,
                        null
                ))
                .toList();
    }
}
