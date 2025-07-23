package rm.tech.ecommerce.module.ecommerce.domain.entities.structure;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rm_structure_translation")
public class StructureTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
