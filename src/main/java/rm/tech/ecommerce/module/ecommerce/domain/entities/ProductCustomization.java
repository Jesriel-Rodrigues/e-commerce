package rm.tech.ecommerce.module.ecommerce.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rm_product_customization")
public class ProductCustomization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
