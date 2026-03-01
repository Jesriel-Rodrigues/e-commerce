package rm.tech.ecommerce.module.ecommerce.domain.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
