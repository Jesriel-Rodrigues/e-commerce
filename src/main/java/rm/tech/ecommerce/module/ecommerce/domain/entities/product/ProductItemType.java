package rm.tech.ecommerce.module.ecommerce.domain.entities.product;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.entities.store.Store;

@Data
@Entity
@Table(name = "rm_product_item_type")
@NoArgsConstructor
public class ProductItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //exemplos: tipo miolo, tipo capa, tipo miudezas, tipo colante
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;
}
