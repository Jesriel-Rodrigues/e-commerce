package rm.tech.ecommerce.module.ecommerce.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import rm.tech.ecommerce.module.ecommerce.domain.emuns.CustomType;

@Data
// @Entity
public class CustomizationTemplate {
    
    // @Id
    private Long id;

    private CustomType customType;
}
