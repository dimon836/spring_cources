package com.example.spring_cources.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors()

                .and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/swagger-ui/index.html/**").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()

                .and()
                .build();
    }

}
