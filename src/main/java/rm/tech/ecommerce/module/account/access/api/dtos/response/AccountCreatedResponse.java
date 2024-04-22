package rm.tech.ecommerce.module.account.access.api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreatedResponse {
    
    private Long id;
    private String email;
    private String token;
    private Long expiresIn;
}
