package rm.tech.ecommerce.module.account.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreAddressResponse {

    private Long id;
    
    private String zipCode;

    private String street;

    private String city;

    private String state;

    private Long houseNumber;
}
