package rm.tech.ecommerce.module.account.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StoreAddressPost(

    Long id,

    @NotBlank
    String zipCode,

    @NotBlank
    String street,

    @NotBlank
    String city,

    @NotBlank
    String state,

    @NotNull
    Long houseNumber
) {
    
}
