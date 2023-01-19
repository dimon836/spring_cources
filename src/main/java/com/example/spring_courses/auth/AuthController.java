package com.example.spring_courses.auth;

import com.example.spring_courses.auth.types.AuthRequest;
import com.example.spring_courses.auth.types.AuthResponse;
import com.example.spring_courses.auth.types.RegistrationRequest;
import com.example.spring_courses.config.jwt.JwtProvider;
import com.example.spring_courses.user.UserService;
import com.example.spring_courses.user.entity.UserEntity;
import com.example.spring_courses.user.repo.UserEntityRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final UserEntityRepo userEntityRepo;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider, UserEntityRepo userEntityRepo) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.userEntityRepo = userEntityRepo;
    }

    @PostMapping(value = "sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseEntity<AuthResponse>> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        if (userService.findByLoginAndPassword(registrationRequest.getLogin(), registrationRequest.getPassword()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(registrationRequest.getLogin());
        userEntity.setPassword(registrationRequest.getPassword());
        userService.saveUser(userEntity);

        return new ResponseEntity<>(auth(new AuthRequest(registrationRequest.getLogin(),
                registrationRequest.getPassword())), HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> auth(@RequestBody @Valid @NotNull AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (userEntity == null) {
            return new ResponseEntity<>(new AuthResponse(), HttpStatus.NOT_FOUND);
        }
        String token = jwtProvider.generateToken(userEntity.getLogin(), false);
        String refreshToken = jwtProvider.generateToken(userEntity.getLogin(), true);
        return new ResponseEntity<>(new AuthResponse(userEntity, token, refreshToken), HttpStatus.OK);
    }
}
