package rm.tech.ecommerce.module.account.access.api.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest( 
    @NotBlank(message = "Email não pode ser vazio") String email, 
    @NotBlank(message = "Senha não pode ser vazia") String password) {
    
}
