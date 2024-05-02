package rm.tech.ecommerce.module.ecommerce.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.emuns.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @Entity(name = "rm_product")
public class Product {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private List<ProductCustomization> customizations;

    private String color;

    private float preco;
    
    private Size size;


}
