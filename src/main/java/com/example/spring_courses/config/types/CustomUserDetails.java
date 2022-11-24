package com.example.spring_courses.config.types;

import com.example.spring_courses.user.entity.SwaggerUserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    @Getter
    @Setter
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromSwaggerUserEntityToCustomUserDetails(SwaggerUserEntity swaggerUserEntity) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.id = swaggerUserEntity.getId();
        customUserDetails.username = swaggerUserEntity.getLogin();
        customUserDetails.password = swaggerUserEntity.getPassword();
        customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(swaggerUserEntity.getRoleEntity().getName()));
        return customUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
