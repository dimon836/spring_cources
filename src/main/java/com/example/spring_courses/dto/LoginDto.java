package com.example.spring_courses.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginDto {
    private String user;
    private String pass;

    @Schema(name = "user", required = true)
    public String getUser() {
        return user;
    }

    @Schema(name = "pass", required = true)
    public String getPass() {
        return pass;
    }
}
