package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.common.contants.ShopeeConstants;
import com.example.spring_demo_app.common.exception.AppAuthenticationException;
import com.example.spring_demo_app.common.exception.LoginException;
import com.example.spring_demo_app.data.model.UserModel;
import com.example.spring_demo_app.data.services.UserService;
import com.example.spring_demo_app.domain.entity.UserEntity;
import com.example.spring_demo_app.repository.UserRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
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



    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper, OkHttpClient client) {
        this.repository = repository;
        this.mapper = mapper;
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



}
