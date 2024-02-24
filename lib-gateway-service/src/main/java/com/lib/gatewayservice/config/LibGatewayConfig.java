package com.lib.gatewayservice.config;



import org.springframework.cloud.gateway.route.RouteLocator;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
@Configuration
public class LibGatewayConfig {
	
	private final String[] urls= {
			"/actuator/health/**",
			"/eureka/**",
			"/webjars/**",
			"/v3/api-docs/**",
			"/swagger-ui.html",
			"/swagger-resources/**",
			"/lib-book-service/v3/api-docs/**",
			"/lib-member-service/v3/api-docs/**",
			"/lib-utility-service/v3/api-docs/**"
	};
	
	@Bean
	SecurityWebFilterChain getSecurityWebFilterChain(ServerHttpSecurity http) {
		http.csrf(csrf->csrf.disable());
		http.authorizeExchange(auth->auth
				.pathMatchers(urls).permitAll()
				.anyExchange().authenticated()
				);
		http.oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()));
		return http.build();
	}
	

	
	@Bean
	RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("lib-book-service",r->r
						.path("/book/**","/lib-book-service/v3/api-docs")
						.uri("lb://lib-book-service")
						)

                .route("lib-member-service", r -> r
                		.path("/member/**","/lib-member-service/v3/api-docs")
                		.uri("lb://lib-member-service")
                		)
                .route("lib-utility-service", r -> r
                		.path("/utility/**","/lib-utility-service/v3/api-docs")
                		.uri("lb://lib-utility-service")
                		)

                .build();

	}
	
	
	
	   @Bean
	   OpenAPI gateWayOpenApi() {   
	       return new OpenAPI()
	       		.info(new Info()
	       				.title("Library API Gateway Documentation ")
	                       .description("Documentation for all the Microservices in Library API Gateway")
	                       .version("v1.0.0")
	                       .contact(new Contact()
	                               .name("Parmeshwar S Tayade")
	                               .email("psstayade@gmail.com")
	                               )
	                       );
	   }
	   
	   @Bean
		 public CorsWebFilter getCorsWebFilter() {
			 final CorsConfiguration corsConfig = new CorsConfiguration();
			 corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:8181"));        
		     corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "HEAD", "PUT"));
		     corsConfig.addAllowedHeader("Access-Control-Allow-Origin");
		     final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		     source.registerCorsConfiguration("/**", corsConfig);
		     return new CorsWebFilter(source);
		  }


	
	
}
