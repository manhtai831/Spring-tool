package com.example.spring_demo_app.domain.service.impl;

import com.example.spring_demo_app.data.model.UserModel;
import com.example.spring_demo_app.domain.entity.UserEntity;
import com.example.spring_demo_app.domain.service.UserService;
import com.example.spring_demo_app.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<UserModel> getAllUser() {

        List<UserEntity> entities = repository.findAll();

        return entities.stream().map(e -> mapper.map(e, UserModel.class)).collect(Collectors.toList());
    }

    @Override
    public UserModel getUserInfo() {
        return null;
    }

    @Override
    public Optional<UserDetails> getUserById(Long id) {
        Optional<UserEntity> entity = repository.findById(id);
        return Optional.of(mapper.map(entity, UserDetails.class));
    }


    @Override
    public UserModel getUserWithAuthentication() {
        return null;
    }

    @Override
    public UserModel createUser() {
        return null;
    }

    @Override
    public UserModel updateUser() {
        return null;
    }
}
