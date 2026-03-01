package rm.tech.ecommerce.module.ecommerce.api.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemSize;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemStatus;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemResponse {

    private Long id;
    private String name;
    private Long typeId;
    private String typeName;
    private BigDecimal price;
    private ProductItemStatus status;
    private Boolean countStock;
    private Double quantity;
    private ProductItemSize size;
    private String color;
}
