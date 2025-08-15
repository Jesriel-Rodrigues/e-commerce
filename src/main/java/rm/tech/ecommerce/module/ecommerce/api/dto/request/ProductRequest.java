package rm.tech.ecommerce.module.ecommerce.api.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    
    private Long id;

    private String name;

    private String description;

    private List<Long> customizations;

    private Long structureId;

    private List<String> photos;
}
