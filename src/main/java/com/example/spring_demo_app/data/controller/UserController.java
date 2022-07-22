package com.example.spring_demo_app.data.controller;

import com.example.spring_demo_app.common.exception.LoginException;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.ResponseList;
import com.example.spring_demo_app.data.model.UserModel;
import com.example.spring_demo_app.domain.service.impl.JwtServiceImpl;
import com.example.spring_demo_app.domain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/app/v1/user")
public class UserController {

    private final UserServiceImpl userService;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserServiceImpl userService, JwtServiceImpl jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    private BaseResponse login(@RequestBody @Valid UserModel model) throws LoginException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(model, null, model.getRoles()));

        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user.setToken(jwtService.generateTokenLogin(user.getId()));

        return BaseResponse.success(user);
    }

    @GetMapping("/info")
    private BaseResponse getUserInfo() throws LoginException {
        UserModel model = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return BaseResponse.success(model);
    }

    @GetMapping("/list")
    private BaseResponse getAllUser(@RequestParam(defaultValue = "0") Integer pageIndex,
                                    @RequestParam(defaultValue = "50") Integer pageSize) {

        Page<UserModel> users = userService.getAllUser(pageIndex, pageSize);
        return ResponseList.ok(users);
    }

    @PostMapping("/register")
    private BaseResponse createUser(@RequestBody @Valid UserModel user) {
        UserModel model = userService.createUser(user);
        return BaseResponse.success(model);
    }
}
