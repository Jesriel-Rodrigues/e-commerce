package rm.tech.ecommerce.module.ecommerce.domain.entities.structure;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "rm_structure_type")
public class StructureType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="structure_size_id", referencedColumnName="id")
    private StructureSize structureSize;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="structure_translation_id", referencedColumnName="id")
    private StructureTranslation structureTranslation;
}
