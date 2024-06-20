package rm.tech.ecommerce.properties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "ecommerce")
public class EcommerceProp {
    
    @NotNull
    private Security security;

    @Data
    public static class Security {

        @NotNull
        private RSAPublicKey publicKey;

        @NotNull
        private RSAPrivateKey privateKey;
    } 
}
