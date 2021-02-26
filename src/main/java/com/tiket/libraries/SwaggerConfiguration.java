package com.tiket.libraries;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private TypeResolver resolver;

	@Autowired
	public void setResolver(TypeResolver resolver) {
		this.resolver = resolver;
	}

	@Bean
	public Docket init() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("tix-nr-webflux-api")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.tiket"))
				.paths(regex("/.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("tix-nr-webflux API")
				.description("tix-nr-webflux API reference for developers")
				.version("1.0.0-1").build();
	}

}