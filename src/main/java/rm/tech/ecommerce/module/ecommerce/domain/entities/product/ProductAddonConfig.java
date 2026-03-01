package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.entities.store.Store;
import rm.tech.ecommerce.module.ecommerce.domain.enums.AddonConfigStatus;

import java.util.List;

@Data
@Entity
@Table(name = "rm_product_addon_config")
@NoArgsConstructor
public class ProductAddonConfig extends AuditCommom {

    private String name;

    @Enumerated(EnumType.STRING)
    private AddonConfigStatus status;

    @OneToMany(mappedBy = "productAddonConfig", fetch = FetchType.LAZY)
    private List<ProductAddonOptionsConfig> addonOptionsConfigs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;
}
