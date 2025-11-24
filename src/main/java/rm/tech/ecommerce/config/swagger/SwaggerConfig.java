package rm.tech.ecommerce.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "API", version = "v1"),
        security = {
                @SecurityRequirement(name = "bearerAuth")

        }
)
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {

    @Bean
    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
        SwaggerUiConfigParameters params = new SwaggerUiConfigParameters(new SwaggerUiConfigProperties());
        params.setDocExpansion("none");

        return params;
    }

    @Bean
    public OpenApiCustomizer controllerTagsCustomizer() {
        return openApi -> {
            if (openApi.getPaths() == null) return;

            // Set para evitar tags duplicadas
            Set<String> addedTags = new HashSet<>();

            openApi.getPaths().forEach((path, pathItem) -> {
                pathItem.readOperations().forEach(operation -> {

                    // Descobre de qual controller veio a rota
                    if (operation.getTags() == null || operation.getTags().isEmpty()) {
                        return;
                    }

                    String originalTag = operation.getTags().get(0);

                    // Gera nome e descrição automaticamente
                    String className = originalTag; // Nome do controller

                    if (!addedTags.contains(className)) {
                        io.swagger.v3.oas.models.tags.Tag tag = new io.swagger.v3.oas.models.tags.Tag();
                        tag.setName(className);
                        tag.setDescription("Endpoints do controller " + className);

                        openApi.addTagsItem(tag);
                        addedTags.add(className);
                    }
                });
            });
        };
    }
}
