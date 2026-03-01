package rm.tech.ecommerce.module.ecommerce.api.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemSize;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSkuResponse {

    private Long id;
    private Long productId;
    private BigDecimal price;
    private ProductItemSize size;
    private List<ProductSkuItemResponse> items;
}
