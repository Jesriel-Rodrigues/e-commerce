package rm.tech.ecommerce.module.ecommerce.api.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.AddonConfigStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddonConfigRequest {

    private Long id;
    private String name;
    private AddonConfigStatus status;
    private Long productId;
    private List<ProductAddonOptionsConfigRequest> addonOptionsConfigs;
}
