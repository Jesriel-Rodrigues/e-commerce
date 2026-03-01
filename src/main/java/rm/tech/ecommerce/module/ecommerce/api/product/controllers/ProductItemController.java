package rm.tech.ecommerce.module.ecommerce.api.product.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.ProductItemRequest;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.response.ProductItemResponse;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductItemService;

import java.util.List;

@RestController
@RequestMapping("/v1/product-item")
@AllArgsConstructor
public class ProductItemController {

    private final IProductItemService productItemService;

    @PostMapping
    public ResponseEntity<ProductItemResponse> create(@Valid @RequestBody ProductItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productItemService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductItemResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductItemRequest request) {
        return ResponseEntity.ok(productItemService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductItemResponse> findById(@PathVariable Long id) {
        return productItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductItemResponse>> findAll() {
        return ResponseEntity.ok(productItemService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
