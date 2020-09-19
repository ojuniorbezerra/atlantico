package com.atlantico.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Value("${security.oauth2.client.client-id}")
    private String clientId;


    @Value("${security.oauth2.client.scope}")
    private String[] scopes;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;
        
    
    @Autowired
    private SecurityScheme securityScheme;

    @Autowired
    private SecurityContext securityContext;


	@Bean
	public Docket swaggerPersonApi10() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("api-1.0")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage("com.atlantico.controller"))
	            .paths(PathSelectors.any())
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("1.0").title("USER API").description("Documentation User API v1.0")
	        		.build())
	        .securitySchemes(Collections.singletonList(securityScheme))
            .securityContexts(Collections.singletonList(securityContext));
	}
	
}
