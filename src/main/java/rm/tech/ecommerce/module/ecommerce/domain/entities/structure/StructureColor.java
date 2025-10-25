package rm.tech.ecommerce.module.ecommerce.domain.entities.structure;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "rm_structure_color")
public class StructureColor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name="structure_id", referencedColumnName="id")
    private Structure structure;
}
