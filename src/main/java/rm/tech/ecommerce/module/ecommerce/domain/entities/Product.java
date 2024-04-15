package rm.tech.ecommerce.module.ecommerce.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    private Long id;

    private String name;

    private String description;

    private List<CustomizationProduct> customizations;
}
