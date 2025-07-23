package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.Structure;

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

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="structure_id", referencedColumnName="id")
    private Structure structure;
}
