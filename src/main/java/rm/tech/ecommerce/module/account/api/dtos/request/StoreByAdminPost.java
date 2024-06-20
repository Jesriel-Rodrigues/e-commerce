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

    @NotBlank(message = "Atributo [NAME] não pode ser vazio")
    private String name;

    @NotBlank(message = "Atributo [EMAIL] não pode ser vazio")
    private String email;

    private String cnpj;

    @Valid
    @NotNull
    private StoreAddressPost storeAddress;

    @Valid
    @NotNull
    private StoreCustomizePost storeCustomize;

    @NotNull(message = "Atributo [ACCOUNTID] não pode ser vazio")
    private Long accountId;
}
