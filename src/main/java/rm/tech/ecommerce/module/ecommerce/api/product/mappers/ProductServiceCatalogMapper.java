package rm.tech.ecommerce.module.ecommerce.api.product.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductServiceCatalogRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductServiceCatalogResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductService;

@Component
@lombok.RequiredArgsConstructor
public class ProductServiceCatalogMapper {

    private final ModelMapper modelMapper;

    public ProductService toEntity(ProductServiceCatalogRequest request) {
        return modelMapper.map(request, ProductService.class);
    }

    public void updateEntity(ProductService entity, ProductServiceCatalogRequest request) {
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setStatus(request.getStatus());
    }

    public ProductServiceCatalogResponse toResponse(ProductService entity) {
        return modelMapper.map(entity, ProductServiceCatalogResponse.class);
    }
}
