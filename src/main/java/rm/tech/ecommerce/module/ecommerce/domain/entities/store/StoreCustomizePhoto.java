package rm.tech.ecommerce.module.ecommerce.domain.entities.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.account.domain.enums.TypePhoto;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rm_store_customize_photo")
public class StoreCustomizePhoto {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "size")
	private Long size;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_photo")
	private TypePhoto typePhoto;
	
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "store_id", referencedColumnName = "id")
	@JsonIgnoreProperties("photos")
	private Store store;
}
