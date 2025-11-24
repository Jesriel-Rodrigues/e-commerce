package rm.tech.ecommerce.module.ecommerce.services.customizationfields.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.customization.dto.request.CustomizationFieldRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationField;

import java.util.Optional;

public interface ICustomizationFieldsService {

    Optional<CustomizationField> findOptionalById(Long id);

    CustomizationField findById(Long id);

    CustomizationField create (CustomizationFieldRequest request);
}
