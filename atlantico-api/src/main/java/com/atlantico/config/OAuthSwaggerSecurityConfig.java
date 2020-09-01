package com.atlantico.config;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;

@Configuration
public class OAuthSwaggerSecurityConfig {

	@Value("${security.oauth2.client.client-id}")
    private String clientId;


    @Value("${security.oauth2.client.scope}")
    private String[] scopes;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;
    
    
    @Value("${security.oauth2.client.auth-server}")
    private String auth_server;


    @Bean
    public OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = Stream.of(this.scopes)
        		.map(sc -> new AuthorizationScope(sc, sc))
        		.collect(Collectors.toList());
        
        List<GrantType> grantTypes = new ArrayList();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(auth_server+"/oauth/token");

        grantTypes.add(creGrant);

        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);

    }

    @Bean
    public SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/v1.0/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        return Collections.singletonList(new SecurityReference("oauth2schema", scopes()));
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(clientId, secret, "", "", "", ApiKeyVehicle.HEADER, "", " ");
    }

	
	private AuthorizationScope[] scopes() {
	    return Stream.of(this.scopes).map(sc -> new AuthorizationScope(sc, sc)).toArray(AuthorizationScope[]::new);
	}
	
}