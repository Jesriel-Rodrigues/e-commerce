package rm.tech.ecommerce.module.ecommerce.domain.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDimension {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double height;

    private double width;

    private double deep;

}
