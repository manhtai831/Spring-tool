package com.example.spring_demo_app.repository;

import com.example.spring_demo_app.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntitiesByUsernameAndPassword(String u, String p);

    Optional<UserEntity> findUserEntitiesByUsername(String u);


}
