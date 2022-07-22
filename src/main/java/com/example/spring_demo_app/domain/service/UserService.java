package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.common.exception.AppAuthenticationException;
import com.example.spring_demo_app.common.exception.LoginException;
import com.example.spring_demo_app.data.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<UserModel> getAllUser(Integer index, Integer size);

    UserModel login(String userName,String password) throws LoginException;

    UserModel getUserInfo();

    UserModel getUserById(Long id) throws AppAuthenticationException;

    UserModel createUser(UserModel userModel);

    UserModel updateUser();

}
