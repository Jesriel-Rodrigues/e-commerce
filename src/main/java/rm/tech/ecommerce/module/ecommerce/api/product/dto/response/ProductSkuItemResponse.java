package rm.tech.ecommerce.module.ecommerce.api.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSkuItemResponse {

    private Long id;
    private Long productItemId;
    private String productItemName;
    private Double quantity;
}
