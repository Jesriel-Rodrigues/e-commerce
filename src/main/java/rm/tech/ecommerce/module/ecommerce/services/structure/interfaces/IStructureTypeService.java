package rm.tech.ecommerce.module.ecommerce.services.structure.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.structure.dto.request.StructureTypeRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.StructureType;

import java.util.Optional;

public interface IStructureTypeService {
    Optional<StructureType> getOptionalById(Long id);

    StructureType getById(Long id);

    StructureType create(StructureTypeRequest request);
}
