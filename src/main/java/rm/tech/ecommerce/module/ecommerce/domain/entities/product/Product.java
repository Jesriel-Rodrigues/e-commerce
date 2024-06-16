package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rm_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    private List<ProductCustomizationFields> customizations;

    @OneToMany(mappedBy="product", fetch=FetchType.EAGER)
    private List<ProductPhoto> productPhotos;
}
