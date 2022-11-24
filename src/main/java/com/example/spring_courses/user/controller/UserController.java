package com.example.spring_courses.user.controller;

import com.example.spring_courses.user.entity.UserEntity;
import com.example.spring_courses.user.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
@RequestMapping("/user")
@Api("user requests api")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ApiOperation("add user")
    public void addUser(UserEntity user) {
        userRepository.save(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation("delete user by id")
    public void deleteUserById(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = {"application/json"})
    @ApiOperation("get all users")
    public Iterator<UserEntity> getAllUsers() {
        return userRepository.findAll().iterator();
    }

}
