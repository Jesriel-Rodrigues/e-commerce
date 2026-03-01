package rm.tech.ecommerce.module.ecommerce.services.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.domain.entities.order.Order;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.order.OrderRepository;
import rm.tech.ecommerce.module.ecommerce.services.order.interfaces.IOrderService;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository repository;

    @Override
    public Order findEntityByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado: " + id));
    }
}
