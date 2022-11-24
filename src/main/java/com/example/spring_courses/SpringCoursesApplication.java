package com.example.spring_courses;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SecurityScheme(type = SecuritySchemeType.APIKEY, name = "api_key", in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition( info = @Info(title = "Apply Default Global SecurityScheme in springdoc-openapi", version = "1.0.0"),
                    security = {@SecurityRequirement(name = "api_key")})
@SpringBootApplication
public class SpringCoursesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCoursesApplication.class, args);
    }

}
