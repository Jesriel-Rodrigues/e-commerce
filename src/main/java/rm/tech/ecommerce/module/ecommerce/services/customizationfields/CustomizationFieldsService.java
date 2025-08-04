package rm.tech.ecommerce.module.ecommerce.services.customizationfields;

import org.springframework.stereotype.Service;
import rm.tech.ecommerce.module.ecommerce.api.dto.request.CustomizationFieldsRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationFields;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.enums.CustomizationFieldsStatus;
import rm.tech.ecommerce.module.ecommerce.services.customizationfields.interfaces.ICustomizationFields;

import java.util.List;

@Service
public class CustomizationFieldsService implements ICustomizationFields {


    @Override
    public CustomizationFields buildNewCustomizationField(CustomizationFieldsRequest request){

        return new CustomizationFields(
                        request.getName(),
                        request.getCustomType(),
                        request.isNotNull(),
                        request.getSequence(),
                        CustomizationFieldsStatus.ENABLE,
                        null,
                        request.getValue(),
                        null
                );
    }

}
