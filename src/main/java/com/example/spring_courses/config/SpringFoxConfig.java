package com.example.spring_courses.config;

import com.example.spring_courses.auth.types.AuthRequest;
import com.example.spring_courses.auth.types.AuthResponse;
import com.example.spring_courses.auth.types.RegistrationRequest;
import com.example.spring_courses.config.plugins.EmailAnnotationPlugin;
import com.fasterxml.classmate.TypeResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.boot.starter.autoconfigure.SpringfoxConfigurationProperties;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {
    @Bean
    public Docket apiDocket(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.spring_courses"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, new ArrayList<>(
                        Arrays.asList(
                                new ResponseBuilder().code("500")
                                        .description("[get] 500 message").build(),
                                new ResponseBuilder().code("403")
                                        .description("[get] 403 message, forbidden!").build(),
                                new ResponseBuilder().code("400")
                                        .description("[get] Auth pls before doing that!").build()
                        )
                ))
                .globalResponses(HttpMethod.POST, new ArrayList<>(
                        Arrays.asList(
                                new ResponseBuilder().code("500")
                                        .description("[post] 500 message").build(),
                                new ResponseBuilder().code("403")
                                        .description("[post] 403 message, forbidden!").build(),
                                new ResponseBuilder().code("400")
                                        .description("[post] Auth pls before doing that!").build()
                        )
                ))
                .additionalModels(
                        typeResolver.resolve(AuthRequest.class),
                        typeResolver.resolve(AuthResponse.class),
                        typeResolver.resolve(RegistrationRequest.class)
                )
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .genericModelSubstitutes(ResponseEntity.class);
    }


    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScopeBuilder().scope("read").description("read access").build();
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        SecurityReference securityReference = SecurityReference.builder().reference("test").scopes(authorizationScopes).build();
        return List.of(securityReference);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                new Contact("Dima Halych", "www.example.com", "dg1472534@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    @Bean
    public EmailAnnotationPlugin emailPlugin() {
        return new EmailAnnotationPlugin();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
