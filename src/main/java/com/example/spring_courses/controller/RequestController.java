package com.example.spring_courses.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Requests")
public class RequestController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "returns the word \"hello\"")
    public String hello() {
        return "hello";
    }

}
