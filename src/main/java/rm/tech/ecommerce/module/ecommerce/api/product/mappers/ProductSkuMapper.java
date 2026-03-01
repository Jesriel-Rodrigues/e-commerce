package rm.tech.ecommerce.module.ecommerce.api.product.mappers;

import org.springframework.stereotype.Component;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductSkuDefinitionRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductSkuItemRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductSkuItemResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductSkuResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSku;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSkuItem;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductSkuMapper {

    public ProductSkuResponse toResponse(ProductSku entity) {
        ProductSkuResponse response = ProductSkuResponse.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .size(entity.getSize())
                .build();
        if (entity.getProduct() != null) {
            response.setProductId(entity.getProduct().getId());
        }
        if (entity.getSkuItems() != null && !entity.getSkuItems().isEmpty()) {
            response.setItems(
                    entity.getSkuItems().stream()
                            .map(this::toSkuItemResponse)
                            .collect(Collectors.toList())
            );
        } else {
            response.setItems(Collections.emptyList());
        }
        return response;
    }

    public ProductSkuItemResponse toSkuItemResponse(ProductSkuItem entity) {
        ProductSkuItemResponse response = ProductSkuItemResponse.builder()
                .id(entity.getId())
                .quantity(entity.getQuantity())
                .build();
        if (entity.getProductItem() != null) {
            response.setProductItemId(entity.getProductItem().getId());
            response.setProductItemName(entity.getProductItem().getName());
        }
        return response;
    }

    public ProductSku toEntity(ProductSkuDefinitionRequest request, rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product product) {
        ProductSku sku = new ProductSku();
        sku.setProduct(product);
        sku.setSize(request.getSize());
        return sku;
    }

    public ProductSkuItem toSkuItemEntity(ProductSkuItemRequest request, ProductSku sku, ProductItem productItem) {
        ProductSkuItem item = new ProductSkuItem();
        item.setProductSku(sku);
        item.setProductItem(productItem);
        item.setQuantity(request.getQuantity());
        return item;
    }
}
