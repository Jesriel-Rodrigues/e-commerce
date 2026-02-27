package rm.tech.ecommerce.module.ecommerce.domain.entities.product;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "rm_product_sku_item",
            joinColumns = @JoinColumn(name = "product_sku_id"),
            inverseJoinColumns = @JoinColumn(name = "product_item_id")
    )
    private List<ProductSkuItem> skuitems;
}
