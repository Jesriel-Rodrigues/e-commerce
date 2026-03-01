package rm.tech.ecommerce.module.ecommerce.api.product.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductPhoto;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSku;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductAddonConfigService;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
@lombok.RequiredArgsConstructor
public class ProductMapper {

    private final ModelMapper modelMapper;
    private final ProductSkuMapper productSkuMapper;
    private final IProductAddonConfigService productAddonConfigService;

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        if (request.getPhotos() != null && !request.getPhotos().isEmpty())
            product.setProductPhotos(buildProductPhotos(product, request.getPhotos()));
        product.setProductAddonConfig(productAddonConfigService.findEntityByIdOrThrow(request.getProductAddonConfigId()));
        return product;
    }

    public void updateEntity(Product entity, ProductRequest request) {
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        if (request.getPhotos() != null) {
            entity.setProductPhotos(buildProductPhotos(entity, request.getPhotos()));
        }
    }

    public ProductResponse toResponse(Product entity) {
        ProductResponse response = modelMapper.map(entity, ProductResponse.class);
        if (entity.getModel() != null) {
            response.setModelId(entity.getModel().getId());
        }
        if (entity.getStore() != null) {
            response.setStoreId(entity.getStore().getId());
        }
        if (entity.getProductPhotos() != null && !entity.getProductPhotos().isEmpty()) {
            response.setPhotos(
                    entity.getProductPhotos().stream()
                            .sorted((a, b) -> Long.compare(a.getSequence() != null ? a.getSequence() : 0, b.getSequence() != null ? b.getSequence() : 0))
                            .map(ProductPhoto::getUrl)
                            .collect(Collectors.toList())
            );
        } else {
            response.setPhotos(Collections.emptyList());
        }
        if (entity.getProductSkus() != null && !entity.getProductSkus().isEmpty()) {
            response.setSkus(
                    entity.getProductSkus().stream()
                            .map(productSkuMapper::toResponse)
                            .collect(Collectors.toList())
            );
        } else {
            response.setSkus(Collections.emptyList());
        }
        return response;
    }

    public List<ProductPhoto> buildProductPhotos(Product product, List<String> photoUrls) {
        if (photoUrls == null || photoUrls.isEmpty()) {
            return Collections.emptyList();
        }
        AtomicLong sequence = new AtomicLong(1);
        return photoUrls.stream()
                .map(url -> new ProductPhoto(null, url, sequence.getAndIncrement(), product))
                .collect(Collectors.toList());
    }
}
