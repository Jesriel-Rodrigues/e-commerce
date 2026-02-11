package rm.tech.ecommerce.module.ecommerce.services.structure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.api.structure.dto.request.StructureTypeRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.StructureType;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.structure.StructureTypeRepository;
import rm.tech.ecommerce.module.ecommerce.services.structure.interfaces.IStructureTypeService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StructureTypeServiceImpl implements IStructureTypeService {

    private final StructureTypeRepository structureTypeRepository;

    @Override
    public Optional<StructureType> getOptionalById(Long id) {
        return structureTypeRepository.findById(id);
    }

    @Override
    public StructureType getById(Long id) {
        return structureTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StructureType not found with id: " + id));
    }

    @Override
    public StructureType create(StructureTypeRequest request) {
        return null;
    }
}
