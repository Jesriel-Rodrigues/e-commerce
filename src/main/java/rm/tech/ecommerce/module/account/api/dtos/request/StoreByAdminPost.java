package rm.tech.ecommerce.module.account.api.dtos.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreByAdminPost{

    // private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String cnpj;

    @Valid
    @NotNull
    private StoreAddressPost storeAddress;

    @Valid
    @NotNull
    private StoreCustomizePost storeCustomize;

    @NotNull
    private Long accountId;
}
