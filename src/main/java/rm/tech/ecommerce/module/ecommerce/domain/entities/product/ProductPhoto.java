package rm.tech.ecommerce.module.ecommerce.domain.entities.product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "rm_product_photo")
public class ProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

	private Long sequence;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.REFRESH)
    @JoinColumn(name="product_id", referencedColumnName="id")
    private Product product;
}
