package rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.enums.SelectItemStatus;


@Data
@Entity
@Table(name="rm_select_items")
@NoArgsConstructor
public class SelectItem extends AuditCommom {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private SelectItemStatus status;

    private BigDecimal value;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customization_product_id", referencedColumnName="id")
    private CustomizationField customizationProduct;

    public SelectItem(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, String name, String description, SelectItemStatus status, BigDecimal value, CustomizationField customizationProduct) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.name = name;
        this.description = description;
        this.status = status;
        this.value = value;
        this.customizationProduct = customizationProduct;
    }

    public SelectItem(String name, String description, SelectItemStatus status, BigDecimal value, CustomizationField customizationProduct) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.value = value;
        this.customizationProduct = customizationProduct;
    }
}
