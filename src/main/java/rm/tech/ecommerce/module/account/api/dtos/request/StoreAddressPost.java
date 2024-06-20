package rm.tech.ecommerce.module.account.api.dtos.request;

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
public class StoreAddressPost{

    @NotBlank(message = "Atributo [zipCode] não pode ser vazio")
    private String zipCode;

    @NotBlank(message = "Atributo [street] não pode ser vazio")
    private String street;

    @NotBlank(message = "Atributo [city] não pode ser vazio")
    private String city;

    @NotBlank(message = "Atributo [state] não pode ser vazio")
    private String state;

    @NotNull(message = "Atributo [houseNumber] não pode ser vazio")
    private Long houseNumber;
}
