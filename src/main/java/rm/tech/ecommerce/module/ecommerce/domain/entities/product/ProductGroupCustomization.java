package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationField;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "product_group_customization")
@NoArgsConstructor
public class ProductGroupCustomization extends AuditCommom {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customization_field_id")
    private CustomizationField customizationFields;

    private Long priority;

    public ProductGroupCustomization(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, Product product, CustomizationField customizationFields, Long priority) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.product = product;
        this.customizationFields = customizationFields;
        this.priority = priority;
    }

    public ProductGroupCustomization(Product product, CustomizationField customizationFields, Long priority) {
        this.product = product;
        this.customizationFields = customizationFields;
        this.priority = priority;
    }
}
