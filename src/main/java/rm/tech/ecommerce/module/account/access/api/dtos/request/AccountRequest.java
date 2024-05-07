package rm.tech.ecommerce.module.account.access.api.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record AccountRequest (
    @NotBlank(message = "Email não pode ser vazio") String email,
    @NotBlank(message = "Nome de usuario não pode ser vazio") String userName, 
    @NotBlank(message = "Senha não pode ser vazia") String password){
    
}
