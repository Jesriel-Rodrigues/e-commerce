package rm.tech.ecommerce.module.account.api.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreCustomizePost{
    
    private String urlCustomize;
    
    private String colorPrimary;
    
    private String colorSecundary;
}