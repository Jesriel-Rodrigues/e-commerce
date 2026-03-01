package rm.tech.ecommerce.module.ecommerce.domain.entities.product;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.entities.store.Store;

@Data
@Entity
@Table(name = "rm_product")
@NoArgsConstructor
public class Product extends AuditCommom {

    private String name;

    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
    private List<ProductSku> productSkus;

    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
    private List<ProductPhoto> productPhotos;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductAddonConfig> productAddonConfigs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;


    public Product(AuditCommom audit, String name, String description, List<ProductSku> productSkus, List<ProductPhoto> productPhotos) {
        super(audit.getId(), audit.getCreatedDate(), audit.getCreatedBy(), audit.getModifiedDate(), audit.getModifiedBy());
        this.name = name;
        this.description = description;
        this.productSkus = productSkus;
        this.productPhotos = productPhotos;
    }
}
