package rm.tech.ecommerce.module.ecommerce.api.product.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long id;
    private String name;
    private String description;
    private Long productAddonConfigId;
    private Long modelId;
    private Long storeId;
    private List<String> photos;

    /**
     * SKUs dinâmicos: para cada entrada, um SKU é criado com tamanho (opcional) e itens.
     * Ex.: tamanho G + [miolo x1, capa couro x1]; tamanho G + [miolo x1, capa pedrarias x1].
     */
    @Valid
    private List<ProductSkuDefinitionRequest> skuDefinitions;
}
