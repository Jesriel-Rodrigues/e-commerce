package rm.tech.ecommerce.module.ecommerce.services.product;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.api.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductGroupCustomization;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductPhoto;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.Structure;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductRepository;
import rm.tech.ecommerce.module.ecommerce.services.customizationfields.interfaces.ICustomizationFieldsService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductService;
import rm.tech.ecommerce.module.ecommerce.services.structure.interfaces.IStructureService;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductRepository productRepository;

    private final IStructureService structureService;

    private final ICustomizationFieldsService customizationFieldsService;

    @Override
    public Product create(ProductRequest request){

        Structure structure = structureService.getById(request.getStructureId());

        Product product = new Product(
                request.getName(),
                request.getDescription(),
                buildGroupCustomizations(request.getCustomizations()),
                buildPhotos(request.getPhotos()),
                structure);

        return productRepository.save(product);
    }

    private List<ProductGroupCustomization> buildGroupCustomizations(List<Long> customizationIds){

        AtomicLong priority = new AtomicLong(1);

        return customizationIds.stream()
                .map(customId -> new ProductGroupCustomization(
                            null,
                            customizationFieldsService.findById(customId),
                            priority.getAndIncrement()
                    )
                )
                .toList();
    }

    private List<ProductPhoto> buildPhotos(List<String> photosRequest){
        AtomicLong priority = new AtomicLong(1);

        return photosRequest.stream()
                .map(photo -> new ProductPhoto(
                        null,
                        photo,
                        priority.getAndIncrement(),
                        null
                ))
                .toList();
    }



}
