package com.example.spring_courses.auth.types;

import com.example.spring_courses.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private UserEntity user;
    private String token;
    private String refreshToken;
}
