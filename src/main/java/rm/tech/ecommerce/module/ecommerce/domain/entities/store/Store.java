package rm.tech.ecommerce.module.ecommerce.domain.entities.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import rm.tech.ecommerce.module.account.domain.entities.Account;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rm_store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String cnpj;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_address_id", referencedColumnName = "id")
    private StoreAddress storeAddress;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_customize_id", referencedColumnName = "id")
    private StoreCustomize storeCustomize;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "account_owner_id", referencedColumnName = "id")
    private Account owner;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<StoreAccount> accounts;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "store", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("storeCustomize")
    @ToString.Exclude
    @Fetch(FetchMode.SUBSELECT)
    private List<StoreCustomizePhoto> photos;
}
