package rm.tech.ecommerce.module.ecommerce.domain.entities.order;


import jakarta.persistence.*;
import lombok.Data;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductSku;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "rm_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_sku_id", referencedColumnName = "id")
    private ProductSku productSku;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
