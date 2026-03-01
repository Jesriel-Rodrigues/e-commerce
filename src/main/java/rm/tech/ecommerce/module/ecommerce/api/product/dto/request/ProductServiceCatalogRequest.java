package rm.tech.ecommerce.module.ecommerce.api.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemStatus;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceCatalogRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private ProductItemStatus status;
}
