package rm.tech.ecommerce.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Component
@Validated
@Configuration
@ConfigurationProperties("spring.security.oauth2.resourceserver.jwt")
public class AuthorizationProperties {

    @NotBlank
    private String issuerUri;
}
