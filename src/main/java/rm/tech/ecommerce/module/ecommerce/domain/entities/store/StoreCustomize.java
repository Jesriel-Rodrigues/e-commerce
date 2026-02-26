package rm.tech.ecommerce.module.ecommerce.domain.entities.store;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rm_store_customize")
public class StoreCustomize {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url_customize")
    private String urlCustomize;
    
    @Column(name = "color_primary")
    private String colorPrimary;

    @Column(name = "color_secundary")
    private String colorSecundary;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "storeCustomize")
	private Store store;
}
