package rm.tech.ecommerce.module.ecommerce.api.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.ecommerce.domain.entities.product.ProductPhoto;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    
    private Long id;

    private String name;

    private String description;

    private List<ProductCustomizationFieldsRequest> customizations;

    private Long structureId;

    private List<ProductPhotoRequest> photos;
}
