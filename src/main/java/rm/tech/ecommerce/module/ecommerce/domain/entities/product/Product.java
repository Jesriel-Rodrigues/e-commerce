package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.Structure;

@Data
@Entity
@Table(name = "rm_product")
@NoArgsConstructor
public class Product extends AuditCommom {

    private String name;

    private String description;

    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
    private List<ProductGroupCustomization> groupCustomizationFields;

    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
    private List<ProductPhoto> productPhotos;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="structure_id", referencedColumnName="id")
    private Structure structure;


    public Product(String name, String description, List<ProductGroupCustomization> groupCustomizationFields, List<ProductPhoto> productPhotos, Structure structure) {
        super(null, null, null ,null, null);
        this.name = name;
        this.description = description;
        this.groupCustomizationFields = groupCustomizationFields;
        this.productPhotos = productPhotos;
        this.structure = structure;
    }

    public Product(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, String name, String description, List<ProductGroupCustomization> groupCustomizationFields, List<ProductPhoto> productPhotos, Structure structure) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.name = name;
        this.description = description;
        this.groupCustomizationFields = groupCustomizationFields;
        this.productPhotos = productPhotos;
        this.structure = structure;
    }
}
