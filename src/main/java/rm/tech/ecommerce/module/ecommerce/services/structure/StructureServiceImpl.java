package rm.tech.ecommerce.module.ecommerce.services.structure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rm.tech.ecommerce.exceptions.ResourceNotFoundException;
import rm.tech.ecommerce.module.ecommerce.api.product.dto.request.SctructureRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.structure.Structure;
import rm.tech.ecommerce.module.ecommerce.domain.repositories.structure.StructureRepository;
import rm.tech.ecommerce.module.ecommerce.services.structure.interfaces.IStructureService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StructureServiceImpl implements IStructureService {

    private final StructureRepository structureRepository;

    @Override
    public Optional<Structure> getOptionalById(Long id) {
        return structureRepository.findById(id);
    }

    @Override
    public Structure getById(Long id) {
        return structureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found with id: " + id));
    }

    @Override
    public Structure create(SctructureRequest request) {
        return null;
    }
}
