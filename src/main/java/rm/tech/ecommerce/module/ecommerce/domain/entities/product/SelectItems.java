package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationFields;


@Data
@Entity
@Table(name="rm_select_items")
public class SelectItems {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal value;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customization_product_id", referencedColumnName="id")
    private CustomizationFields customizationProduct;
}
