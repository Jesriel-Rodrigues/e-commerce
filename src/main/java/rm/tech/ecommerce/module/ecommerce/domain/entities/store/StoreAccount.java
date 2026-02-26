package rm.tech.ecommerce.module.ecommerce.domain.entities.store;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.account.domain.entities.Account;
import rm.tech.ecommerce.module.ecommerce.domain.enums.StoreAccountRole;
import rm.tech.ecommerce.module.ecommerce.domain.enums.StoreAccountStatus;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rm_account_store")
public class StoreAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StoreAccountStatus status;

    @Enumerated(EnumType.STRING)
    private StoreAccountRole role;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
}
