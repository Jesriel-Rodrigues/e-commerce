package rm.tech.ecommerce.module.ecommerce.domain.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.order.OrderItemAddon;

import java.util.List;

public interface OrderItemAddonRepository extends JpaRepository<OrderItemAddon, Long> {

    List<OrderItemAddon> findByOrderId(Long orderId);
}
