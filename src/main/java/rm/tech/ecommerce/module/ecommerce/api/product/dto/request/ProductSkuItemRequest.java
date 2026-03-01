package rm.tech.ecommerce.module.ecommerce.api.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSkuItemRequest {

    @NotNull(message = "productItemId é obrigatório")
    private Long productItemId;

    @NotNull(message = "quantity é obrigatório")
    @Positive
    private Double quantity;
}
