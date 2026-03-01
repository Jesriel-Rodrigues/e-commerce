package rm.tech.ecommerce.module.ecommerce.services.order.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.order.dto.request.OrderItemAddonRequest;
import rm.tech.ecommerce.module.ecommerce.api.order.dto.response.OrderItemAddonResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.order.OrderItemAddon;

import java.util.List;
import java.util.Optional;

public interface IOrderItemAddonService {

    OrderItemAddonResponse create(OrderItemAddonRequest request);

    OrderItemAddonResponse update(Long id, OrderItemAddonRequest request);

    Optional<OrderItemAddonResponse> findById(Long id);

    List<OrderItemAddonResponse> findByOrderId(Long orderId);

    void deleteById(Long id);

    OrderItemAddon findEntityByIdOrThrow(Long id);
}
