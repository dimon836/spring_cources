package com.example.spring_courses.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "login")
    private String login;

    @NotNull
    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "age")
    @Min(value = 0, message = "age can't be less than 0")
    @Max(value = 120, message = "age can't be greater than 120")
    private int age;

    @Column(name = "email")
    @Email(regexp=".*@.*\\..*", message = "Email should be valid")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

}
