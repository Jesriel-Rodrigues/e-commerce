package rm.tech.ecommerce.module.ecommerce.api.product.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductAddonConfigRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductAddonConfigResponse;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductAddonConfigService;

import java.util.List;

@RestController
@RequestMapping("/v1/product-addon-config")
@AllArgsConstructor
public class ProductAddonConfigController {

    private final IProductAddonConfigService service;

    @PostMapping
    public ResponseEntity<ProductAddonConfigResponse> create(@Valid @RequestBody ProductAddonConfigRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductAddonConfigResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductAddonConfigRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAddonConfigResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductAddonConfigResponse>> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.findByProductId(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
