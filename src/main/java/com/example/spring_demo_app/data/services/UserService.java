package com.example.spring_demo_app.data.services;

import com.example.spring_demo_app.common.exception.AppAuthenticationException;
import com.example.spring_demo_app.common.exception.LoginException;
import com.example.spring_demo_app.data.model.UserModel;
import okhttp3.Response;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface UserService {

    Page<UserModel> getAllUser(Integer index, Integer size);

    UserModel login(String userName, String password) throws LoginException;

    UserModel getUserInfo();

    UserModel getUserById(Long id) throws AppAuthenticationException;

    UserModel createUser(UserModel userModel);

    UserModel updateUser();

    Response login(String json) throws IOException;

}
