package rm.tech.ecommerce.module.ecommerce.api.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.AddonConfigStatus;
import rm.tech.ecommerce.module.ecommerce.domain.enums.AddonOptionFieldType;
import rm.tech.ecommerce.module.ecommerce.domain.enums.AddonOptionType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddonOptionsConfigRequest {

    private Long id;
    private String name;
    private AddonOptionFieldType fieldType;
    private AddonOptionType type;
    private boolean required;
    private Integer sequence;
    private AddonConfigStatus status;
    private String options;
    private Long parentAddonOptionsId;
    private List<Long> productItemIds;
    private List<Long> productServiceIds;
}
