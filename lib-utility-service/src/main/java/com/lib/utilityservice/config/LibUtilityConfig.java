package com.lib.utilityservice.config;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
@Configuration
@SecurityScheme(
		name = "security_auth",
		in = SecuritySchemeIn.HEADER,
		openIdConnectUrl = "http://localhost:8181/admin/master/console/#/spring-boot-microservices-realm",
		type = SecuritySchemeType.OAUTH2,
		flows = @OAuthFlows(
				clientCredentials  = @OAuthFlow(
						tokenUrl = "http://localhost:8181/realms/spring-boot-microservices-realm/protocol/openid-connect/token",
						scopes = { @OAuthScope(
								name = "openid",
								description = "openid scope"
								)}
						)
				)
		)
public class LibUtilityConfig {
	@Bean
	ModelMapper getModelMapper() {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	
 @Bean
   OpenAPI gateWayOpenApi() {   
       return new OpenAPI()
       		.servers(List.of(new Server().url("http://localhost:8081")))
       		.info(new Info()
       				.title("Lib Utility Service Documentation ")
                       .description("Documentation for all the Rest PAI in Lib Utility Service")
                       .version("v1.0.0")
                       .contact(new Contact()
                               .name("Parmeshwar S Tayade")
                               .email("psstayade@gmail.com")
                               )
                       );
       }	
}
