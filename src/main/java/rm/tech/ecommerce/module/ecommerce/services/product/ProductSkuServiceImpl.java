package rm.tech.ecommerce.module.ecommerce.services.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rm.tech.ecommerce.exceptions.ResourceBadRequestException;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductSkuDefinitionRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductSkuItemRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductSkuResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.mappers.ProductSkuMapper;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSku;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSkuItem;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductSkuItemRepository;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductSkuRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductItemService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductSkuService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductSkuServiceImpl implements IProductSkuService {

    private final ProductSkuRepository productSkuRepository;
    private final ProductSkuItemRepository productSkuItemRepository;
    private final ProductSkuMapper productSkuMapper;
    private final IProductItemService productItemService;

    @Override
    @Transactional
    public void syncSkusForProduct(Product product, List<ProductSkuDefinitionRequest> skuDefinitions) {
        if (skuDefinitions == null) return;

        saveSkus(product, skuDefinitions);
    }

    public void prepareSyncSkus(List<ProductSkuDefinitionRequest> skuDefinitions) {
        if (skuDefinitions == null || skuDefinitions.isEmpty()) return;
        for (ProductSkuDefinitionRequest def : skuDefinitions) {
            validateNoDuplicateItemTypeInSku(def);
        }
    }

    public void saveSkus(Product product, List<ProductSkuDefinitionRequest> skuDefinitions) {
        deleteByProductId(product.getId());
        if (skuDefinitions.isEmpty()) return;

        prepareSyncSkus(skuDefinitions);

        for (ProductSkuDefinitionRequest def : skuDefinitions) {
            ProductSku sku = productSkuMapper.toEntity(def, product);
            sku = productSkuRepository.save(sku);

            BigDecimal totalPrice = BigDecimal.ZERO;
            for (ProductSkuItemRequest itemReq : def.getItems()) {
                ProductItem productItem = productItemService.findEntityByIdOrThrow(itemReq.getProductItemId());
                ProductSkuItem skuItem = productSkuMapper.toSkuItemEntity(itemReq, sku, productItem);
                productSkuItemRepository.save(skuItem);
                if (productItem.getPrice() != null && itemReq.getQuantity() != null) {
                    totalPrice = totalPrice.add(productItem.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity())));
                }
            }
            sku.setPrice(totalPrice);
            productSkuRepository.save(sku);
        }
    }

    private void validateNoDuplicateItemTypeInSku(ProductSkuDefinitionRequest def) {
        Map<Long, Long> typeIdToProductItemId = new HashMap<>();
        for (ProductSkuItemRequest itemReq : def.getItems()) {
            ProductItem productItem = productItemService.findEntityByIdOrThrow(itemReq.getProductItemId());
            if (productItem.getType() == null) continue;
            Long typeId = productItem.getType().getId();
            Long productItemId = productItem.getId();
            Long existingItemId = typeIdToProductItemId.get(typeId);
            if (existingItemId != null && !existingItemId.equals(productItemId)) {
                String typeName = productItem.getType().getName() != null ? productItem.getType().getName() : "id " + typeId;
                throw new ResourceBadRequestException(
                        "SKU não pode ter mais de um item do mesmo tipo (ex.: dois miolos diferentes). Tipo duplicado: " + typeName);
            }
            typeIdToProductItemId.put(typeId, productItemId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSkuResponse> findByProductId(Long productId) {
        return productSkuRepository.findByProductId(productId).stream()
                .map(productSkuMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteByProductId(Long productId) {
        List<ProductSku> skus = productSkuRepository.findByProductId(productId);
        for (ProductSku sku : skus) {
            productSkuItemRepository.deleteByProductSkuId(sku.getId());
        }
        productSkuRepository.deleteAll(skus);
    }
}
