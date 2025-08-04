package rm.tech.ecommerce.module.ecommerce.domain.entities.structure;

import jakarta.persistence.*;
import lombok.Data;
import rm.tech.ecommerce.module.commom.AuditCommom;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "rm_structure")
public class Structure  extends AuditCommom {

    private String name;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="structure_type_id", referencedColumnName="id")
    private StructureType structureType;

    @OneToMany(mappedBy="structure", fetch=FetchType.EAGER)
    private List<StructureColor> structureColors;

    public Structure(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, String name, StructureType structureType, List<StructureColor> structureColors) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.name = name;
        this.structureType = structureType;
        this.structureColors = structureColors;
    }

    public Structure(String name, StructureType structureType, List<StructureColor> structureColors) {
        this.name = name;
        this.structureType = structureType;
        this.structureColors = structureColors;
    }
}

