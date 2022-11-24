package com.example.spring_courses.config.types;

import com.example.spring_courses.user.SwaggerUserService;
import com.example.spring_courses.user.entity.SwaggerUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService {
    private final SwaggerUserService swaggerUserService;

    @Autowired
    public CustomUserDetailsService(SwaggerUserService swaggerUserService) {
        this.swaggerUserService = swaggerUserService;
    }

    public CustomUserDetails loadUserByLogin(String login) {
        SwaggerUserEntity userEntity = swaggerUserService.findByLogin(login);
        return CustomUserDetails.fromSwaggerUserEntityToCustomUserDetails(userEntity);
    }

}
