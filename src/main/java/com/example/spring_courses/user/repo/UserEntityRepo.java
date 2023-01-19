package com.example.spring_courses.user.repo;

import com.example.spring_courses.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}
