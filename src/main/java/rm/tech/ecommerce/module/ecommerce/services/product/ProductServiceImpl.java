package rm.tech.ecommerce.module.ecommerce.services.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rm.tech.ecommerce.exceptions.ResourceBadRequestException;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductSkuDefinitionRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductSkuResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.mappers.ProductMapper;
import rm.tech.ecommerce.module.ecommerce.api.product.mappers.ProductSkuMapper;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSku;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSkuItem;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductRepository;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductSkuItemRepository;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductSkuRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductItemService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final ProductSkuRepository productSkuRepository;
    private final ProductSkuItemRepository productSkuItemRepository;
    private final ProductMapper mapper;
    private final ProductSkuMapper productSkuMapper;
    private final IProductItemService productItemService;

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product entity = mapper.toEntity(request);
        entity = repository.save(entity);

        if (request.getSkuDefinitions() != null && !request.getSkuDefinitions().isEmpty()) {
            createSkusForProduct(entity, request.getSkuDefinitions());
        }

        entity = repository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado após criação"));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product entity = findEntityByIdOrThrow(id);
        mapper.updateEntity(entity, request);
        entity = repository.save(entity);

        if (request.getSkuDefinitions() != null) {
            List<ProductSku> existingSkus = productSkuRepository.findByProductId(id);
            for (ProductSku sku : existingSkus) {
                productSkuItemRepository.deleteByProductSkuId(sku.getId());
            }
            productSkuRepository.deleteAll(existingSkus);

            if (!request.getSkuDefinitions().isEmpty()) {
                createSkusForProduct(entity, request.getSkuDefinitions());
            }
        }

        entity = repository.findById(entity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado após atualização"));
        return mapper.toResponse(entity);
    }

    private void createSkusForProduct(Product product, List<ProductSkuDefinitionRequest> skuDefinitions) {
        for (ProductSkuDefinitionRequest def : skuDefinitions) {
            validateNoDuplicateItemTypeInSku(def);

            ProductSku sku = productSkuMapper.toEntity(def, product);
            sku = productSkuRepository.save(sku);

            BigDecimal totalPrice = BigDecimal.ZERO;
            for (var itemReq : def.getItems()) {
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

    /**
     * Um SKU não pode ter dois ProductItems diferentes do mesmo tipo (ex.: dois miolos diferentes).
     * A quantidade do mesmo item pode ser maior que 1 (ex.: miolo x2).
     */
    private void validateNoDuplicateItemTypeInSku(ProductSkuDefinitionRequest def) {
        Map<Long, Long> typeIdToProductItemId = new HashMap<>();
        for (var itemReq : def.getItems()) {
            ProductItem productItem = productItemService.findEntityByIdOrThrow(itemReq.getProductItemId());
            if (productItem.getType() == null) {
                continue;
            }
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
    public Optional<ProductResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSkuResponse> findSkusByProductId(Long productId) {
        return productSkuRepository.findByProductId(productId).stream()
                .map(productSkuMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Product product = findEntityByIdOrThrow(id);
        List<ProductSku> skus = productSkuRepository.findByProductId(id);
        for (ProductSku sku : skus) {
            productSkuItemRepository.deleteByProductSkuId(sku.getId());
        }
        productSkuRepository.deleteAll(skus);
        repository.deleteById(id);
    }

    @Override
    public Product findEntityByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));
    }
}
