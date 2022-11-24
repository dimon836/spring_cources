package com.example.spring_courses.user.repo;

import com.example.spring_courses.user.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Repository
public interface RoleEntityRepo extends JpaRepository<RoleEntity, Long> {
    @NotNull @NotEmpty RoleEntity findByName(String name);
}
