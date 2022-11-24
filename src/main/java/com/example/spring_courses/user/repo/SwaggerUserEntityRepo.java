package com.example.spring_courses.user.repo;

import com.example.spring_courses.user.entity.SwaggerUserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwaggerUserEntityRepo extends JpaRepository<SwaggerUserEntity, Long> {
    @NotNull List<SwaggerUserEntity> findAll();

    SwaggerUserEntity findByLogin(@NotNull String login);
}
