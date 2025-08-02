package rm.tech.ecommerce.module.ecommerce.domain.entities.structure;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "rm_structure")
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="structure_type_id", referencedColumnName="id")
    private StructureType structureType;

    @OneToMany(mappedBy="structure", fetch=FetchType.EAGER)
    private List<StructureColor> structureColors;
}

