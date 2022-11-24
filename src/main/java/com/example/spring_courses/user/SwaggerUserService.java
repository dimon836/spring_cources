package com.example.spring_courses.user;

import com.example.spring_courses.user.entity.SwaggerUserEntity;
import com.example.spring_courses.user.repo.RoleEntityRepo;
import com.example.spring_courses.user.repo.SwaggerUserEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SwaggerUserService {

    private final SwaggerUserEntityRepo swaggerUserEntityRepo;

    private final RoleEntityRepo roleEntityRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SwaggerUserService(SwaggerUserEntityRepo swaggeruserEntityRepo,
                              RoleEntityRepo roleEntityRepo,
                              @Lazy PasswordEncoder passwordEncoder) {
        this.swaggerUserEntityRepo = swaggeruserEntityRepo;
        this.roleEntityRepo = roleEntityRepo;
        this.passwordEncoder = passwordEncoder;

    }

    public SwaggerUserEntity findByLogin(String login) {
        return swaggerUserEntityRepo.findByLogin(login);
    }

}
