package rm.tech.ecommerce.module.ecommerce.api.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Long modelId;
    private Long storeId;
    private List<String> photos;
    private List<ProductSkuResponse> skus;
}
