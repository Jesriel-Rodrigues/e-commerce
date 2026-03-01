package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "rm_product_addon_options_config_service")
@NoArgsConstructor
public class ProductAddonOptionsConfigService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_addon_options_config_id", referencedColumnName = "id")
    private ProductAddonOptionsConfig productAddonOptionsConfig;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_service_id", referencedColumnName = "id")
    private ProductService productService;
}
