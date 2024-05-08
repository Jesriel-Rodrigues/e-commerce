package rm.tech.ecommerce.module.ecommerce.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import rm.tech.ecommerce.module.ecommerce.domain.emuns.CustomType;

@Data
@Entity
public class ProductCustomizationTemplate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private CustomType customType;
}
