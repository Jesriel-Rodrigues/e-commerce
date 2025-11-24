package rm.tech.ecommerce.module.ecommerce.api.customization.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SelectItemRequest {

    private String name;

    private String description;

    private BigDecimal value;
}
