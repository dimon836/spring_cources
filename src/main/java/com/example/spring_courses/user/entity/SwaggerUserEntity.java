package com.example.spring_courses.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "swagger_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SwaggerUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    // @UniqueElements
    @Column(name = "login")
    @JsonIgnore
    @ToString.Exclude
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

}
