package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/*
 * http://localhost:8080/swagger-ui/index.html
 * http://localhost:8080/v3/api-docs
 */


@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPI() {
    	

        return new OpenAPI()
                .info(new Info()
                        .title("API de Preguntas")
                        .version("1.0.0")
                        .description("API para gestionar preguntas")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}