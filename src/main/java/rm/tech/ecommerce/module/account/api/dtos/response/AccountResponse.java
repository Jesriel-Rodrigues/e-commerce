package rm.tech.ecommerce.module.account.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class AccountResponse {
    
    private Long id;

    private String userName;

    private String email;
}
