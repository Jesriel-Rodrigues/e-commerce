package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.enums.ProductItemStatus;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "rm_product_service")
@NoArgsConstructor
public class ProductService extends AuditCommom {

    private String name;
    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductItemStatus status;
}
