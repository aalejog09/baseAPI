package com.base.api.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Clase de configuracion para exponer la documentacion del API basado en Swagger.
 * 
 * @author AAlejo
 * */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Autowired
	private Environment env;

	@Bean
	 Docket api() {
	  return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
			  .build().apiInfo(apiInfoMetaData()).securityContexts(Arrays.asList(securityContext()))
             .securitySchemes(Arrays.asList(apiKey()));
	    }

	    private ApiInfo apiInfoMetaData() {
//	    	System.out.println("DATA SWAGGER title: "+ env.getProperty("swagger.config.title"));
//	    	System.out.println("DATA SWAGGER description: "+ env.getProperty("swagger.config.description"));
//	    	System.out.println("DATA SWAGGER contact name: "+ env.getProperty("swagger.config.contact.name"));
//	    	System.out.println("DATA SWAGGER contact url: "+ env.getProperty("swagger.config.contact.url"));
//	    	System.out.println("DATA SWAGGER contact email: "+ env.getProperty("swagger.config.contact.email"));
//	    	
//	    	System.out.println("DATA SWAGGER TITLE: "+ env.getProperty("swagger.config.license"));
//	    	System.out.println("DATA SWAGGER TITLE: "+ env.getProperty("swagger.config.license.url"));
//	    	System.out.println("DATA SWAGGER TITLE: "+ env.getProperty("swagger.config.version"));
	        return new ApiInfoBuilder().title(env.getProperty("swagger.config.title"))
	                .description(env.getProperty("swagger.config.description"))
	                .contact(new Contact(
	                		env.getProperty("swagger.config.contact.name"), 
	                		env.getProperty("swagger.config.contact.url"), 
	                		env.getProperty("swagger.config.contact.email"))
	                		)
	                .license(env.getProperty("swagger.config.license"))
	                .licenseUrl(env.getProperty("swagger.config.license.url"))
	                .version(env.getProperty("swagger.config.version"))
	                .build();
	        
	        
	    }
	    
	    private ApiKey apiKey() {
	        return new ApiKey("Bearer ","Authorization","header");
	    }
	    private SecurityContext securityContext() {
	        return SecurityContext.builder().securityReferences(defaultAuth()).build();
	    }
	    private List<SecurityReference> defaultAuth(){
	        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("Bearer ", authorizationScopes));
	    }
	 
}
