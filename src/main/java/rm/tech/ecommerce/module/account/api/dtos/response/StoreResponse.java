package rm.tech.ecommerce.module.account.api.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rm.tech.ecommerce.module.account.domain.entities.Account;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {
    
    private Long id;

    private String name;

    private String email;

    private String cnpj;

    private StoreAddressResponse storeAddress;

    private StoreCustomizeResponse storeCustomize;

	private Account account;

	private List<StoreCustomizePhotoResponse> photos;
}
