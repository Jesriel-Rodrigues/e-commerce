package rm.tech.ecommerce.module.ecommerce.services.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductItemRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductItemResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.mappers.ProductItemMapper;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItemType;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductItemRepository;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductItemTypeRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductItemServiceImpl implements IProductItemService {

    private final ProductItemRepository repository;
    private final ProductItemTypeRepository typeRepository;
    private final ProductItemMapper mapper;

    @Override
    @Transactional
    public ProductItemResponse create(ProductItemRequest request) {
        ProductItem entity = prepareForCreate(request);
        return mapper.toResponse(save(entity));
    }

    @Override
    @Transactional
    public ProductItemResponse update(Long id, ProductItemRequest request) {
        ProductItem entity = prepareForUpdate(id, request);
        return mapper.toResponse(save(entity));
    }

    public ProductItem prepareForCreate(ProductItemRequest request) {
        ProductItemType type = typeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de item não encontrado: " + request.getTypeId()));
        return mapper.toEntity(request, type);
    }

    public ProductItem prepareForUpdate(Long id, ProductItemRequest request) {
        ProductItem entity = findEntityByIdOrThrow(id);
        ProductItemType type = typeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de item não encontrado: " + request.getTypeId()));
        mapper.updateEntity(entity, request, type);
        return entity;
    }

    public ProductItem save(ProductItem productItem) {
        return repository.save(productItem);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductItemResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductItemResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findEntityByIdOrThrow(id);
        repository.deleteById(id);
    }

    @Override
    public ProductItem findEntityByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductItem não encontrado: " + id));
    }
}
