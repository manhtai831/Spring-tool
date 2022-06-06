package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.data.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserModel> getAllUser();



    UserModel getUserInfo();

    Optional<UserDetails> getUserById(Long id);

    UserModel getUserWithAuthentication();

    UserModel createUser();

    UserModel updateUser();
}
