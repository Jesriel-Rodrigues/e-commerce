package rm.tech.ecommerce.module.ecommerce.services.product;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.api.dto.request.ProductCustomizationFieldsRequest;
import rm.tech.ecommerce.module.ecommerce.domain.entities.customizationfields.CustomizationFields;

@Service
@AllArgsConstructor
public class ProductCustomizationFieldsServiceImpl {
    

    public CustomizationFields create(ProductCustomizationFieldsRequest request){
        return null;
    }
}
