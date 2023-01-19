package com.example.spring_courses.user.controller;

import com.example.spring_courses.user.entity.UserEntity;
import com.example.spring_courses.user.repo.UserEntityRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequestMapping("/user")
@Api("user requests api")
@SecurityRequirement(name = "api_key")
public class UserController {

    private final UserEntityRepo userRepository;

    @Autowired
    public UserController(UserEntityRepo userRepository) {
        this.userRepository = userRepository;
    }

    @PutMapping(value = "/add")
    @ApiOperation("add user")
    public void addUser(UserEntity user) {
        userRepository.save(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation("delete user by id")
    public void deleteUserById(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("get all users")
    public Iterator<UserEntity> getAllUsers() {
        return userRepository.findAll().iterator();
    }

}
