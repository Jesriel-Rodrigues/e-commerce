package rm.tech.ecommerce.module.ecommerce.services.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductServiceCatalogRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductServiceCatalogResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.mappers.ProductServiceCatalogMapper;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductService;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.ProductServiceCatalogRepository;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductServiceCatalogService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceCatalogServiceImpl implements IProductServiceCatalogService {

    private final ProductServiceCatalogRepository repository;
    private final ProductServiceCatalogMapper mapper;

    @Override
    public ProductServiceCatalogResponse create(ProductServiceCatalogRequest request) {
        ProductService entity = mapper.toEntity(request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public ProductServiceCatalogResponse update(Long id, ProductServiceCatalogRequest request) {
        ProductService entity = findEntityByIdOrThrow(id);
        mapper.updateEntity(entity, request);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public Optional<ProductServiceCatalogResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public List<ProductServiceCatalogResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        findEntityByIdOrThrow(id);
        repository.deleteById(id);
    }

    @Override
    public ProductService findEntityByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço de catálogo não encontrado: " + id));
    }
}
