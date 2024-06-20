package rm.tech.ecommerce.module.ecommerce.api.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectItemsRequest {
    
    private Long id;

    private String name;

    private String description;

    private BigDecimal value;

    private Long customizationProductId;
}
