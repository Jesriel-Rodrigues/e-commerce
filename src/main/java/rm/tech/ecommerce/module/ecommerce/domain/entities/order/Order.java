package rm.tech.ecommerce.module.ecommerce.domain.entities.order;

import jakarta.persistence.*;
import lombok.Data;
import rm.tech.ecommerce.module.ecommerce.domain.entities.store.StoreAccount;
import rm.tech.ecommerce.module.ecommerce.domain.enums.OrderStatus;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "rm_order")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "price_total")
    private BigDecimal priceTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private StoreAccount account;
}
