package rm.tech.ecommerce.module.ecommerce.domain.repositories.structure;

import org.springframework.data.jpa.repository.JpaRepository;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.StructureType;

public interface StructureTypeRepository extends JpaRepository<StructureType, Long> {
}
