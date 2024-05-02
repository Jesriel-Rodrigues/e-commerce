package rm.tech.ecommerce.module.ecommerce.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDimension {

    private Long id;

    private double height;

    private double width;

    private double deep;

}
