package rm.tech.ecommerce.module.ecommerce.api.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemAddonRequest {

    private Long addonId;
    private Long parentOrderItemAddonId;
    private Long selectedProductItemId;
    private Long selectedProductServiceId;
    private String value;
    private BigDecimal price;
    private Long orderId;
}
