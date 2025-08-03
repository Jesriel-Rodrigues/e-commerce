package rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.Product;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.SelectItems;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.enums.CustomizationFieldsStatus;
import rm.tech.ecommerce.module.ecommerce.domain.enums.CustomType;

@Data
@Entity
@Table(name = "rm_product_customization_fields")
@AllArgsConstructor
public class CustomizationFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
	@Column(name = "type")
    private CustomType customType;

    @Column(name = "not_null")
    private boolean notNull;

    private Long sequence;

    @Enumerated(EnumType.STRING)
    private CustomizationFieldsStatus status;

    @OneToMany(mappedBy="customizationProduct", fetch=FetchType.EAGER)
    private List<SelectItems> items;

    private BigDecimal value;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name="product_id", referencedColumnName="id")
    private Product product;
}
