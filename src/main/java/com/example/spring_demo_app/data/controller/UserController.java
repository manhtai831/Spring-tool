package com.example.spring_demo_app.data.controller;

import com.example.spring_demo_app.common.exception.AppException;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.ResponseList;
import com.example.spring_demo_app.data.model.UserModel;
import com.example.spring_demo_app.domain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/app/v1/user")
public class UserController {

   private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    private BaseResponse getAllUser() {
        List<UserModel> users = userService.getAllUser();
        return ResponseList.ok(1,users);
    }

    @PostMapping()
    private BaseResponse createUser(@RequestBody @Valid UserModel user) {
        return BaseResponse.success(user);
    }
}
