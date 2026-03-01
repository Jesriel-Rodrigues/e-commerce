package rm.tech.ecommerce.module.ecommerce.api.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemSize;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * Define um SKU na criação/atualização do produto.
 * O preço do SKU é calculado automaticamente pela soma dos preços dos ProductItems (price * quantity).
 * Ex.: tamanho G com itens [miolo x1, capa couro x1] ou [miolo x1, capa pedrarias x1].
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSkuDefinitionRequest {

    /** Tamanho da variante (opcional). Ex.: P, M, G. */
    private ProductItemSize size;

    @NotEmpty(message = "Cada SKU deve ter ao menos um item")
    @Valid
    private List<ProductSkuItemRequest> items;
}
