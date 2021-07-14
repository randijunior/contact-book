package com.system.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TAG_1 = "Contato";
    public static final String TAG_2 = "Usuário";
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.system.domain")).build()
                .tags(new Tag(TAG_1, "Gerencia contatos"),new Tag(TAG_2, "Gerencia conta"))
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Rest API Agenda de contatos",
                "Uma api para gerenciar contatos",
                "1.0",
                "Terms of service",
                new Contact("Randi Junior", "github.com/RandiJunior", "randyjunior2011@live.com"),
                "Apache License Version 2.0", "https://github.com/springfox/springfox/blob/master/LICENSE", Collections.emptyList());
    }

}