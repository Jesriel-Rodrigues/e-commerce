package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemSize;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "rm_product_sku")
@NoArgsConstructor
public class ProductSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private BigDecimal price;

    /**
     * Tamanho da variante (opcional). Ex.: P, M, G.
     * Quando o produto tem tamanho, cada SKU pode representar uma combinação tamanho + itens.
     */
    @Enumerated(EnumType.STRING)
    private ProductItemSize size;

    @OneToMany(mappedBy = "productSku", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSkuItem> skuItems;
}
