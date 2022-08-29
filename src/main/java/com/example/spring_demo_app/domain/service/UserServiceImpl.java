package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.common.exception.AppAuthenticationException;
import com.example.spring_demo_app.common.exception.LoginException;
import com.example.spring_demo_app.data.model.UserModel;
import com.example.spring_demo_app.data.services.UserService;
import com.example.spring_demo_app.domain.entity.UserEntity;
import com.example.spring_demo_app.repository.UserRepository;
import okhttp3.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import static com.example.spring_demo_app.common.HeaderStored.MEDIA_TYPE;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;
    private final OkHttpClient client;



    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper, OkHttpClient client) {
        this.repository = repository;
        this.mapper = mapper;
        this.client = client;
    }

    @Override
    public Page<UserModel> getAllUser(Integer index, Integer size) {
        Pageable pageable = PageRequest.of(index, size);
        Page<UserEntity> entities = repository.findAll(pageable);

        return entities.map(userEntity -> mapper.map(userEntity, UserModel.class));
    }

    @Override
    public UserModel login(String userName, String password) throws LoginException {
        Optional<UserEntity> entity = repository.findUserEntitiesByUsernameAndPassword(userName, password);
        if (entity.isEmpty()) throw new LoginException("Sai tài khoản hoặc mật khẩu");
        return mapper.map(entity.get(), UserModel.class);
    }

    @Override
    public UserModel getUserInfo() {
        return null;
    }

    @Override
    public UserModel getUserById(Long id) throws AppAuthenticationException {
        Optional<UserEntity> entity = repository.findById(id);
        System.out.println("BBBBBBBBB: " + entity);
        if (entity.isEmpty()) throw new AppAuthenticationException("FORBIDDEN");

        return mapper.map(entity.get(), UserModel.class);
    }

    @Override
    public UserModel createUser(UserModel userModel) {
        UserEntity entity = mapper.map(userModel, UserEntity.class);

        UserEntity response = repository.saveAndFlush(entity);

        return mapper.map(response, UserModel.class);
    }

    @Override
    public UserModel updateUser() {
        return null;
    }

    @Override
    public Response login(String json) throws IOException {
        RequestBody body = RequestBody.create(json, MEDIA_TYPE);

        Request request = new Request.Builder().url("https://shopee.vn/api/v4/account/login_by_password").post(body).build();

        return client.newCall(request).execute();
    }

}
