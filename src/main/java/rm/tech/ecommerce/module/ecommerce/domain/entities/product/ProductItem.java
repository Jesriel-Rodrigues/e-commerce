package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemSize;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemStatus;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "rm_product_item")
@NoArgsConstructor
public class ProductItem extends AuditCommom {

    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ProductItemType Type;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductItemStatus status;
    @Column(name = "count_stock")
    private boolean countStock;
    private Double quantity;
    private ProductItemSize size;
    private String color;
}
