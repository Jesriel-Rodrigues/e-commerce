package rm.tech.ecommerce.module.ecommerce.services.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rm.tech.ecommerce.module.ecommerce.api.order.dto.request.OrderItemAddonRequest;
import rm.tech.ecommerce.module.ecommerce.api.order.dto.response.OrderItemAddonResponse;
import rm.tech.ecommerce.module.ecommerce.api.order.mappers.OrderItemAddonMapper;
import rm.tech.ecommerce.module.ecommerce.domain.entities.order.Order;
import rm.tech.ecommerce.module.ecommerce.domain.entities.order.OrderItemAddon;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfig;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductService;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.order.OrderItemAddonRepository;
import rm.tech.ecommerce.module.ecommerce.services.order.interfaces.IOrderItemAddonService;
import rm.tech.ecommerce.module.ecommerce.services.order.interfaces.IOrderService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductAddonOptionsConfigService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductItemService;
import rm.tech.ecommerce.module.ecommerce.services.product.interfaces.IProductServiceCatalogService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemAddonServiceImpl implements IOrderItemAddonService {

    private final OrderItemAddonRepository repository;
    private final OrderItemAddonMapper mapper;
    private final IOrderService orderService;
    private final IProductAddonOptionsConfigService productAddonOptionsConfigService;
    private final IProductItemService productItemService;
    private final IProductServiceCatalogService productServiceCatalogService;

    @Override
    public OrderItemAddonResponse create(OrderItemAddonRequest request) {
        OrderItemAddon entity = prepareForCreate(request);
        return mapper.toResponse(save(entity));
    }

    @Override
    public OrderItemAddonResponse update(Long id, OrderItemAddonRequest request) {
        OrderItemAddon entity = prepareForUpdate(id, request);
        return mapper.toResponse(save(entity));
    }

    public OrderItemAddon prepareForCreate(OrderItemAddonRequest request) {
        Order order = request.getOrderId() != null ? orderService.findEntityByIdOrThrow(request.getOrderId()) : null;
        ProductAddonOptionsConfig addon = request.getAddonId() != null ? productAddonOptionsConfigService.findEntityByIdOrThrow(request.getAddonId()) : null;
        OrderItemAddon parent = request.getParentOrderItemAddonId() != null ? findEntityByIdOrThrow(request.getParentOrderItemAddonId()) : null;
        ProductItem selectedItem = request.getSelectedProductItemId() != null ? productItemService.findEntityByIdOrThrow(request.getSelectedProductItemId()) : null;
        ProductService selectedService = request.getSelectedProductServiceId() != null ? productServiceCatalogService.findEntityByIdOrThrow(request.getSelectedProductServiceId()) : null;
        return mapper.toEntity(request, order, addon, parent, selectedItem, selectedService);
    }

    public OrderItemAddon prepareForUpdate(Long id, OrderItemAddonRequest request) {
        OrderItemAddon entity = findEntityByIdOrThrow(id);
        Order order = request.getOrderId() != null ? orderService.findEntityByIdOrThrow(request.getOrderId()) : entity.getOrder();
        ProductAddonOptionsConfig addon = request.getAddonId() != null ? productAddonOptionsConfigService.findEntityByIdOrThrow(request.getAddonId()) : entity.getAddon();
        OrderItemAddon parent = request.getParentOrderItemAddonId() != null ? findEntityByIdOrThrow(request.getParentOrderItemAddonId()) : entity.getParentOrderItemAddon();
        ProductItem selectedItem = request.getSelectedProductItemId() != null ? productItemService.findEntityByIdOrThrow(request.getSelectedProductItemId()) : entity.getSelectedProductItem();
        ProductService selectedService = request.getSelectedProductServiceId() != null ? productServiceCatalogService.findEntityByIdOrThrow(request.getSelectedProductServiceId()) : entity.getSelectedProductService();
        mapper.updateEntity(entity, request, order, addon, parent, selectedItem, selectedService);
        return entity;
    }

    public OrderItemAddon save(OrderItemAddon entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<OrderItemAddonResponse> findById(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    @Override
    public List<OrderItemAddonResponse> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        findEntityByIdOrThrow(id);
        repository.deleteById(id);
    }

    @Override
    public OrderItemAddon findEntityByIdOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new rm.tech.ecommerce.exceptions.ResourceNotFoundException("OrderItemAddon não encontrado: " + id));
    }
}
