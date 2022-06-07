package com.example.spring_demo_app.data.controller;

import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.ResponseList;
import com.example.spring_demo_app.common.security.UserPrinciple;
import com.example.spring_demo_app.data.model.UserModel;
import com.example.spring_demo_app.domain.service.impl.JwtServiceImpl;
import com.example.spring_demo_app.domain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/app/v1/user")
public class UserController {

    private final UserServiceImpl userService;
    private final JwtServiceImpl jwtService;
    private  final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserServiceImpl userService, JwtServiceImpl jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/login")
    private BaseResponse login() {
        UserModel user = userService.login("manhtai831", "19022001");
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(new UserPrinciple("manhtai831", "19022001",null),"c4redential"));

        user.setToken(jwtService.generateTokenLogin(1L));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return BaseResponse.success(user);
    }

    @GetMapping("/list")
    private BaseResponse getAllUser() {
        List<UserModel> users = userService.getAllUser();
        return ResponseList.ok(1, users);
    }

    @PostMapping()
    private BaseResponse createUser(@RequestBody @Valid UserModel user) {
        return BaseResponse.success(user);
    }
}
