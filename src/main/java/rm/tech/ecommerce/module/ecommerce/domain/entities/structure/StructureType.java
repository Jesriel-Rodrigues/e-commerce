package rm.tech.ecommerce.module.ecommerce.domain.entities.structure;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rm_structure_type")
public class StructureType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StructureSize structureSize;

    private StructureTranslation structureTranslation;
}
