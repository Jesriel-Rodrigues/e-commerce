package rm.tech.ecommerce.module.ecommerce.services.order.interfaces;

import rm.tech.ecommerce.module.ecommerce.domain.entities.order.Order;

public interface IOrderService {

    Order findEntityByIdOrThrow(Long id);
}
