package rm.tech.ecommerce.module.ecommerce.api.order.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import rm.tech.ecommerce.module.ecommerce.api.order.dto.request.OrderItemAddonRequest;
import rm.tech.ecommerce.module.ecommerce.api.order.dto.response.OrderItemAddonResponse;
import rm.tech.ecommerce.module.ecommerce.domain.entities.order.OrderItemAddon;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfig;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductService;
import rm.tech.ecommerce.module.ecommerce.domain.entities.order.Order;

@Component
@lombok.RequiredArgsConstructor
public class OrderItemAddonMapper {

    private final ModelMapper modelMapper;

    public OrderItemAddon toEntity(
            OrderItemAddonRequest request,
            Order order,
            ProductAddonOptionsConfig addon,
            OrderItemAddon parent,
            ProductItem selectedProductItem,
            ProductService selectedProductService) {
        OrderItemAddon entity = new OrderItemAddon();
        entity.setOrder(order);
        entity.setAddon(addon);
        entity.setParentOrderItemAddon(parent);
        entity.setSelectedProductItem(selectedProductItem);
        entity.setSelectedProductService(selectedProductService);
        entity.setValue(request.getValue());
        entity.setPrice(request.getPrice());
        return entity;
    }

    public void updateEntity(
            OrderItemAddon entity,
            OrderItemAddonRequest request,
            Order order,
            ProductAddonOptionsConfig addon,
            OrderItemAddon parent,
            ProductItem selectedProductItem,
            ProductService selectedProductService) {
        entity.setOrder(order);
        entity.setAddon(addon);
        entity.setParentOrderItemAddon(parent);
        entity.setSelectedProductItem(selectedProductItem);
        entity.setSelectedProductService(selectedProductService);
        entity.setValue(request.getValue());
        entity.setPrice(request.getPrice());
    }

    public OrderItemAddonResponse toResponse(OrderItemAddon entity) {
        OrderItemAddonResponse response = modelMapper.map(entity, OrderItemAddonResponse.class);
        if (entity.getOrder() != null) {
            response.setOrderId(entity.getOrder().getId());
        }
        if (entity.getAddon() != null) {
            response.setAddonId(entity.getAddon().getId());
        }
        if (entity.getParentOrderItemAddon() != null) {
            response.setParentOrderItemAddonId(entity.getParentOrderItemAddon().getId());
        }
        if (entity.getSelectedProductItem() != null) {
            response.setSelectedProductItemId(entity.getSelectedProductItem().getId());
        }
        if (entity.getSelectedProductService() != null) {
            response.setSelectedProductServiceId(entity.getSelectedProductService().getId());
        }
        return response;
    }
}
