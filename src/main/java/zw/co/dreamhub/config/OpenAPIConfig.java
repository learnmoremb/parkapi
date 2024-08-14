package zw.co.dreamhub.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import zw.co.dreamhub.config.env.InfoEnv;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 14/3/2023
 */

@Configuration
@Component
@RequiredArgsConstructor
public class OpenAPIConfig {

    private final InfoEnv env;

    private static final String SCHEME_NAME = "bearerScheme";
    private static final String SCHEME = "Bearer";

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info().title(env.app().name())
                        .description(env.app().description())
                        .version(String.valueOf(env.app().version()))
                        .contact(new Contact()
                                .name(env.contact().name())
                                .email(env.contact().email())
                                .url(env.contact().url())
                        )
                        .license(new License().name("Apache 2.0").url(env.contact().url())));
        addSecurity(openAPI);
        return openAPI;
    }

    private void addSecurity(OpenAPI openApi) {
        Components components = createComponents();
        SecurityRequirement securityItem = new SecurityRequirement().addList(SCHEME_NAME);

        openApi
                .components(components)
                .addSecurityItem(securityItem);
    }

    private Components createComponents() {
        Components components = new Components();
        components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());

        return components;
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }

}
