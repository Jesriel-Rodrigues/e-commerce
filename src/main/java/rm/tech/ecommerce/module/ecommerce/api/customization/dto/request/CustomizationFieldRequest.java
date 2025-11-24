package rm.tech.ecommerce.module.ecommerce.api.customization.dto.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.CustomType;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomizationFieldRequest {
    

    private Long id;

    private String name;

    private CustomType customType;

    private boolean notNull;

    private Long sequence;

    private List<SelectItemRequest> items;

    private BigDecimal value;
    
    private Long productId;
}
