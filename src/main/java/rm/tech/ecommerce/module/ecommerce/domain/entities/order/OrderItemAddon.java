package rm.tech.ecommerce.module.ecommerce.domain.entities.order;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductAddonOptionsConfig;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductItem;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductService;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "rm_order_item_addon")
@NoArgsConstructor
public class OrderItemAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String value;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addon_id", referencedColumnName = "id")
    private ProductAddonOptionsConfig addon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_order_item_addon_id", referencedColumnName = "id")
    private OrderItemAddon parentOrderItemAddon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_product_item_id", referencedColumnName = "id")
    private ProductItem selectedProductItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_product_service_id", referencedColumnName = "id")
    private ProductService selectedProductService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
