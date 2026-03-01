package rm.tech.ecommerce.module.ecommerce.services.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductAddonConfigRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductAddonOptionsConfigRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductAddonConfigResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.mappers.ProductAddonConfigMapper;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonConfig;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfig;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.product.*;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductAddonConfigService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductAddonOptionsConfigService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductItemService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductServiceCatalogService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductAddonConfigServiceImpl implements IProductAddonConfigService {

    private final ProductAddonConfigRepository addonConfigRepository;
    private final ProductAddonOptionsConfigRepository addonOptionsConfigRepository;
    private final ProductAddonOptionsConfigItemRepository configItemRepository;
    private final ProductAddonOptionsConfigServiceRepository configServiceRepository;
    private final ProductAddonConfigMapper mapper;
    private final IProductService productService;
    private final IProductServiceCatalogService productServiceCatalogService;
    private final IProductItemService productItemService;
    private final IProductAddonOptionsConfigService productAddonOptionsConfigService;

    @Override
    @Transactional
    public ProductAddonConfigResponse create(ProductAddonConfigRequest request) {
        ProductAddonConfig entity = prepareForCreate(request);
        ProductAddonConfig saved = save(entity);
        saveOptionsConfigs(saved, request.getAddonOptionsConfigs());
        return mapper.toResponse(addonConfigRepository.findById(saved.getId()).orElse(saved));
    }

    @Override
    @Transactional
    public ProductAddonConfigResponse update(Long id, ProductAddonConfigRequest request) {
        ProductAddonConfig entity = prepareForUpdate(id, request);
        ProductAddonConfig saved = save(entity);
        deleteOptionsConfigs(saved.getId());
        saveOptionsConfigs(saved, request.getAddonOptionsConfigs());
        return mapper.toResponse(addonConfigRepository.findById(saved.getId()).orElse(saved));
    }

    public ProductAddonConfig prepareForCreate(ProductAddonConfigRequest request) {
        var product = productService.findEntityByIdOrThrow(request.getProductId());
        return mapper.toEntity(request, product);
    }

    public ProductAddonConfig prepareForUpdate(Long id, ProductAddonConfigRequest request) {
        ProductAddonConfig config = findEntityByIdOrThrow(id);
        var product = request.getProductId() != null
                ? productService.findEntityByIdOrThrow(request.getProductId())
                : config.getProduct();
        mapper.updateEntity(config, request, product);
        return config;
    }

    public ProductAddonConfig save(ProductAddonConfig config) {
        return addonConfigRepository.save(config);
    }

    private void deleteOptionsConfigs(Long configId) {
        List<Long> optionIds = addonOptionsConfigRepository.findByProductAddonConfigId(configId).stream()
                .map(ProductAddonOptionsConfig::getId)
                .collect(Collectors.toList());
        if (!optionIds.isEmpty()) {
            configItemRepository.findByProductAddonOptionsConfigIdIn(optionIds).forEach(configItemRepository::delete);
            configServiceRepository.findByProductAddonOptionsConfigIdIn(optionIds).forEach(configServiceRepository::delete);
            addonOptionsConfigRepository.findAllById(optionIds).forEach(addonOptionsConfigRepository::delete);
        }
    }

    private void saveOptionsConfigs(ProductAddonConfig config, List<ProductAddonOptionsConfigRequest> optionsRequests) {
        if (optionsRequests == null || optionsRequests.isEmpty()) return;

        for (ProductAddonOptionsConfigRequest optReq : optionsRequests) {
            var parent = optReq.getParentAddonOptionsId() != null
                    ? productAddonOptionsConfigService.findEntityByIdOrThrow(optReq.getParentAddonOptionsId())
                    : null;
            ProductAddonOptionsConfig opt = mapper.toOptionConfigEntity(optReq, config, parent);
            opt = addonOptionsConfigRepository.save(opt);

            if (optReq.getProductItemIds() != null) {
                for (Long itemId : optReq.getProductItemIds()) {
                    var item = productItemService.findEntityByIdOrThrow(itemId);
                    configItemRepository.save(mapper.toConfigItem(opt, item));
                }
            }
            if (optReq.getProductServiceIds() != null) {
                for (Long serviceId : optReq.getProductServiceIds()) {
                    var service = productServiceCatalogService.findEntityByIdOrThrow(serviceId);
                    configServiceRepository.save(mapper.toConfigService(opt, service));
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductAddonConfigResponse> findById(Long id) {
        return addonConfigRepository.findById(id).map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductAddonConfigResponse> findByProductId(Long productId) {
        return addonConfigRepository.findByProductId(productId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findEntityByIdOrThrow(id);
        deleteOptionsConfigs(id);
        addonConfigRepository.deleteById(id);
    }

    @Override
    public ProductAddonConfig findEntityByIdOrThrow(Long id) {
        return addonConfigRepository.findById(id)
                .orElseThrow(() -> new rm.tech.ecommerce.exceptions.ResourceNotFoundException("Configuração de addon não encontrada: " + id));
    }
}
