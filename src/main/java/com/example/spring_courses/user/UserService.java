package com.example.spring_courses.user;

import com.example.spring_courses.user.entity.RoleEntity;
import com.example.spring_courses.user.entity.UserEntity;
import com.example.spring_courses.user.repo.RoleEntityRepo;
import com.example.spring_courses.user.repo.UserEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserEntityRepo userEntityRepo;
    private final RoleEntityRepo roleEntityRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserEntityRepo userEntityRepo, RoleEntityRepo roleEntityRepo, @Lazy PasswordEncoder passwordEncoder) {
        this.userEntityRepo = userEntityRepo;
        this.roleEntityRepo = roleEntityRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserEntity userEntity) {
        RoleEntity userRoleEntity = roleEntityRepo.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRoleEntity);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntityRepo.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        return userEntityRepo.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}
