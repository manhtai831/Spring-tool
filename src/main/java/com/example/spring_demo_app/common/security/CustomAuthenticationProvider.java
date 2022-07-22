package com.example.spring_demo_app.common.security;

import com.example.spring_demo_app.data.model.UserModel;
import com.example.spring_demo_app.domain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;

    public CustomAuthenticationProvider(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserModel userModel = (UserModel) authentication.getPrincipal();


        UserModel userDetails = userService.login(userModel.getUsername(),userModel.getPassword());

        Authentication authenticated = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getRoles());

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
