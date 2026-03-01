package rm.tech.ecommerce.module.ecommerce.api.product.dto.response;

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
public class ProductServiceCatalogResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private ProductItemStatus status;
}
