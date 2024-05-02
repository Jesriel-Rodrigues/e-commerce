package rm.tech.ecommerce.module.account.api.dtos.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StoreByAdminPost(

    Long id,

    @NotBlank
    String name,

    @NotBlank
    String email,

    String cnpj,

    @Valid
    @NotNull
    StoreAddressPost storeAddress,

    @Valid
    @NotNull
    StoreCustomizePost storeCustomize,

    @NotNull
    Long accountId
) {
    
}
