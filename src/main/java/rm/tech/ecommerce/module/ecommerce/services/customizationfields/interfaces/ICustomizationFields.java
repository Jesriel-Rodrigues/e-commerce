package rm.tech.ecommerce.module.ecommerce.services.customizationfields.interfaces;

import rm.tech.ecommerce.module.ecommerce.api.dto.request.CustomizationFieldsRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationFields;

import java.util.List;

public interface ICustomizationFields {

    CustomizationFields buildNewCustomizationField(CustomizationFieldsRequest request);
}
