package br.com.scandianx.fastdev.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fastdevOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FastDev API")
                        .description("API REST da plataforma FastDev — recursos versionados em /api/v1")
                        .version("v1")
                        .contact(new Contact()
                                .name("FastDev Team")
                                .email("contato@fastdev.local"))
                        .license(new License().name("Proprietary").url("https://example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação e exemplos")
                        .url("/swagger-ui/index.html"));
    }
}

