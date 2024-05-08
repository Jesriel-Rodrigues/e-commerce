package rm.tech.ecommerce.module.ecommerce.domain.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.emuns.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rm_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;
    
    private String color;
    
    private Size size;
    
    private BigDecimal preco;
    
    private List<ProductCustomization> customizations;

    private List<ProductPhotos> photos;
}
