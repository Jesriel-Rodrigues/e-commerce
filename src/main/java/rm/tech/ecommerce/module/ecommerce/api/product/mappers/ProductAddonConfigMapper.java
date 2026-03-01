package rm.tech.ecommerce.module.ecommerce.api.product.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductAddonConfigRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductAddonOptionsConfigRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductAddonConfigResponse;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductAddonOptionsConfigResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@lombok.RequiredArgsConstructor
public class ProductAddonConfigMapper {

    private final ModelMapper modelMapper;

    public ProductAddonConfig toEntity(ProductAddonConfigRequest request, Product product) {
        ProductAddonConfig config = new ProductAddonConfig();
        config.setName(request.getName());
        config.setStatus(request.getStatus());
        config.setProduct(product);
        return config;
    }

    public void updateEntity(ProductAddonConfig config, ProductAddonConfigRequest request, Product product) {
        config.setName(request.getName());
        config.setStatus(request.getStatus());
        config.setProduct(product);
    }

    public ProductAddonOptionsConfig toOptionConfigEntity(
            ProductAddonOptionsConfigRequest request,
            ProductAddonConfig config,
            ProductAddonOptionsConfig parent) {
        ProductAddonOptionsConfig opt = new ProductAddonOptionsConfig();
        opt.setName(request.getName());
        opt.setFieldType(request.getFieldType());
        opt.setType(request.getType());
        opt.setRequired(request.isRequired());
        opt.setSequence(request.getSequence());
        opt.setStatus(request.getStatus());
        opt.setOptions(request.getOptions());
        opt.setProductAddonConfig(config);
        opt.setParentAddonOptions(parent);
        return opt;
    }

    public ProductAddonOptionsConfigItem toConfigItem(ProductAddonOptionsConfig opt, ProductItem item) {
        ProductAddonOptionsConfigItem ci = new ProductAddonOptionsConfigItem();
        ci.setProductAddonOptionsConfig(opt);
        ci.setProductItem(item);
        return ci;
    }

    public ProductAddonOptionsConfigService toConfigService(ProductAddonOptionsConfig opt, ProductService service) {
        ProductAddonOptionsConfigService cs = new ProductAddonOptionsConfigService();
        cs.setProductAddonOptionsConfig(opt);
        cs.setProductService(service);
        return cs;
    }

    public ProductAddonConfigResponse toResponse(ProductAddonConfig entity) {
        ProductAddonConfigResponse response = modelMapper.map(entity, ProductAddonConfigResponse.class);
        if (entity.getProduct() != null) {
            response.setProductId(entity.getProduct().getId());
        }
        if (entity.getAddonOptionsConfigs() != null && !entity.getAddonOptionsConfigs().isEmpty()) {
            response.setAddonOptionsConfigs(
                    entity.getAddonOptionsConfigs().stream()
                            .map(this::toOptionsConfigResponse)
                            .collect(Collectors.toList())
            );
        } else {
            response.setAddonOptionsConfigs(Collections.emptyList());
        }
        return response;
    }

    public ProductAddonOptionsConfigResponse toOptionsConfigResponse(ProductAddonOptionsConfig entity) {
        ProductAddonOptionsConfigResponse response = modelMapper.map(entity, ProductAddonOptionsConfigResponse.class);
        if (entity.getParentAddonOptions() != null) {
            response.setParentAddonOptionsId(entity.getParentAddonOptions().getId());
        }
        if (entity.getConfigItems() != null && !entity.getConfigItems().isEmpty()) {
            response.setProductItemIds(
                    entity.getConfigItems().stream()
                            .map(ci -> ci.getProductItem().getId())
                            .collect(Collectors.toList())
            );
        }
        if (entity.getConfigServices() != null && !entity.getConfigServices().isEmpty()) {
            response.setProductServiceIds(
                    entity.getConfigServices().stream()
                            .map(cs -> cs.getProductService().getId())
                            .collect(Collectors.toList())
            );
        }
        return response;
    }
}
