package rm.tech.ecommerce.module.ecommerce.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ProductCustomization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
