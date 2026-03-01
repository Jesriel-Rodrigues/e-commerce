package rm.tech.ecommerce.module.ecommerce.api.order.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rm.tech.ecommerce.module.ecommerce.api.order.dto.request.OrderItemAddonRequest;
import rm.tech.ecommerce.module.ecommerce.api.order.dto.response.OrderItemAddonResponse;
import rm.tech.ecommerce.module.ecommerce.services.order.interfaces.IOrderItemAddonService;

import java.util.List;

@RestController
@RequestMapping("/v1/order-item-addon")
@AllArgsConstructor
public class OrderItemAddonController {

    private final IOrderItemAddonService service;

    @PostMapping
    public ResponseEntity<OrderItemAddonResponse> create(@Valid @RequestBody OrderItemAddonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemAddonResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody OrderItemAddonRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemAddonResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemAddonResponse>> findByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(service.findByOrderId(orderId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
