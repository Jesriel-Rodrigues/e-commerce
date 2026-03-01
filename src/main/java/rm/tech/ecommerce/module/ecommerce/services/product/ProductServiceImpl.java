package rm.tech.ecommerce.module.ecommerce.services.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductSkuResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.mappers.ProductMapper;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductSkuService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final IProductSkuService productSkuService;

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product entity = prepareForCreate(request);
        Product saved = save(entity);
        if (request.getSkuDefinitions() != null) {
            productSkuService.syncSkusForProduct(saved, request.getSkuDefinitions());
        }
        return mapper.toResponse(reload(saved.getId()));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product entity = prepareForUpdate(id, request);
        Product saved = save(entity);
        if (request.getSkuDefinitions() != null) {
            productSkuService.syncSkusForProduct(saved, request.getSkuDefinitions());
        }
        return mapper.toResponse(reload(saved.getId()));
    }

    public Product prepareForCreate(ProductRequest request) {
        return mapper.toEntity(request);
    }

    public Product prepareForUpdate(Long id, ProductRequest request) {
        Product entity = findEntityByIdOrThrow(id);
        mapper.updateEntity(entity, request);
        return entity;
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    private Product reload(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));
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
        return productSkuService.findByProductId(productId);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findEntityByIdOrThrow(id);
        productSkuService.deleteByProductId(id);
        repository.deleteById(id);
    }

    @Override
    public Product findEntityByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));
    }
}
