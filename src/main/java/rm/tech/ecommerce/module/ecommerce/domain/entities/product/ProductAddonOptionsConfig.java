package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.enums.AddonConfigStatus;
import rm.tech.ecommerce.module.ecommerce.domain.enums.AddonOptionFieldType;
import rm.tech.ecommerce.module.ecommerce.domain.enums.AddonOptionType;

import java.util.List;

@Data
@Entity
@Table(name = "rm_product_addon_options_config")
@NoArgsConstructor
public class ProductAddonOptionsConfig extends AuditCommom {

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "field_type")
    private AddonOptionFieldType fieldType;

    @Enumerated(EnumType.STRING)
    private AddonOptionType type;

    private boolean required;
    private Integer sequence;

    @Enumerated(EnumType.STRING)
    private AddonConfigStatus status;

    /**
     * Opções pré-definidas para SELECT/RADIO (ex: JSON array ou valores separados).
     * Filhos SELECT/RADIO usam options internas; pais vinculam a item/serviço.
     */
    @Column(length = 2000)
    private String options;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_addon_options_id", referencedColumnName = "id")
    private ProductAddonOptionsConfig parentAddonOptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_addon_config_id", referencedColumnName = "id")
    private ProductAddonConfig productAddonConfig;

    @OneToMany(mappedBy = "productAddonOptionsConfig", fetch = FetchType.LAZY)
    private List<ProductAddonOptionsConfigItem> configItems;

    @OneToMany(mappedBy = "productAddonOptionsConfig", fetch = FetchType.LAZY)
    private List<ProductAddonOptionsConfigService> configServices;
}
