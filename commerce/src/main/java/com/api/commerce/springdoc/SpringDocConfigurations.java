package com.api.commerce.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes("bearer-key",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
                .info(new Info()
                    .title("E-commerce")
                .description("API Rest da aplicação de uma E-commerce, contendo as funcionalidades de CRUD de usuarios sendo Client, Seller e Admin, onde um Seller pode criar produtos para a venda, o Admin pode criar novas categorias, e o Client é o usuario, onde pode ver esses produtos e compra-los")
                    .contact(new Contact()
                    .name("Time Backend")
                    .email("backend@commerce.api"))
                    .license(new License()
                        .name("Apache 2.0")
                        .url("http://commerce/api/licenca")));
}
}