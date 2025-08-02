package rm.tech.ecommerce.module.ecommerce.services.structure.interfaces;

import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.Structure;

import java.util.Optional;

public interface IStructureService {
    Optional<Structure> getOptionalById(Long id);

    Structure getById(Long id);
}
