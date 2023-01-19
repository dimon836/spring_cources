package com.example.spring_courses.config.types;

import com.example.spring_courses.user.UserService;
import com.example.spring_courses.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    public CustomUserDetails loadUserByLogin(String login) {
        UserEntity userEntity = userService.findByLogin(login);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }

}
