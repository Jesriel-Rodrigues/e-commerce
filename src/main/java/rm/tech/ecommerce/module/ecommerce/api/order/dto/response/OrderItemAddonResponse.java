package rm.tech.ecommerce.module.ecommerce.api.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemAddonResponse {

    private Long id;
    private Long orderId;
    private Long addonId;
    private Long parentOrderItemAddonId;
    private Long selectedProductItemId;
    private Long selectedProductServiceId;
    private String value;
    private BigDecimal price;
}
