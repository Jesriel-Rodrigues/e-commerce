package rm.tech.ecommerce.module.ecommerce.api.product.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductItemRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductItemResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItemType;

@Component
@lombok.RequiredArgsConstructor
public class ProductItemMapper {

    private final ModelMapper modelMapper;

    public ProductItem toEntity(ProductItemRequest request, ProductItemType type) {
        ProductItem entity = modelMapper.map(request, ProductItem.class);
        entity.setType(type);
        if (request.getCountStock() != null) {
            entity.setCountStock(request.getCountStock());
        }
        return entity;
    }

    public void updateEntity(ProductItem entity, ProductItemRequest request, ProductItemType type) {
        entity.setName(request.getName());
        entity.setType(type);
        entity.setPrice(request.getPrice());
        entity.setStatus(request.getStatus());
        if (request.getCountStock() != null) {
            entity.setCountStock(request.getCountStock());
        }
        entity.setQuantity(request.getQuantity());
        entity.setSize(request.getSize());
        entity.setColor(request.getColor());
    }

    public ProductItemResponse toResponse(ProductItem entity) {
        ProductItemResponse response = modelMapper.map(entity, ProductItemResponse.class);
        if (entity.getType() != null) {
            response.setTypeId(entity.getType().getId());
            response.setTypeName(entity.getType().getName());
        }
        return response;
    }
}
