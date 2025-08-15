package rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.commom.AuditCommom;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.enums.CustomizationFieldsStatus;
import rm.tech.ecommerce.module.ecommerce.domain.enums.CustomType;

@Data
@Entity
@Table(name = "rm_customization_fields")
@NoArgsConstructor
public class CustomizationField extends AuditCommom {

    private String name;

    @Enumerated(EnumType.STRING)
	@Column(name = "custom_type")
    private CustomType customType;

    @Column(name = "not_null")
    private boolean notNull;

    private Long sequence;

    @Enumerated(EnumType.STRING)
    private CustomizationFieldsStatus status;

    @OneToMany(mappedBy="customizationProduct", fetch=FetchType.EAGER)
    private List<SelectItem> items;

    private BigDecimal value;

    public CustomizationField(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, String name, CustomType customType, boolean notNull, Long sequence, CustomizationFieldsStatus status, List<SelectItem> items, BigDecimal value) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.name = name;
        this.customType = customType;
        this.notNull = notNull;
        this.sequence = sequence;
        this.status = status;
        this.items = items;
        this.value = value;
    }

    public CustomizationField(String name, CustomType customType, boolean notNull, Long sequence, CustomizationFieldsStatus status, List<SelectItem> items, BigDecimal value) {
        this.name = name;
        this.customType = customType;
        this.notNull = notNull;
        this.sequence = sequence;
        this.status = status;
        this.items = items;
        this.value = value;
    }
}
