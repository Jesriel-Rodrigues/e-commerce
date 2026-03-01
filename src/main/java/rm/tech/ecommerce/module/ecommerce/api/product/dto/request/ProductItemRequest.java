package rm.tech.ecommerce.module.ecommerce.api.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemSize;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemStatus;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemRequest {

    private String name;
    @NotNull(message = "typeId é obrigatório")
    private Long typeId;
    private BigDecimal price;
    private ProductItemStatus status;
    private Boolean countStock;
    private Double quantity;
    private ProductItemSize size;
    private String color;
}
