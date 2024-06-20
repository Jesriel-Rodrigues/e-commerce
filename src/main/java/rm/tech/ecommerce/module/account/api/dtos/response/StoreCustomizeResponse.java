package rm.tech.ecommerce.module.account.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreCustomizeResponse {
    
    private Long id;

    private String urlCustomize;
    
    private String colorPrimary;

    private String colorSecundary;
}
