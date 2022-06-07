package com.example.spring_demo_app.common.security;

import com.example.spring_demo_app.domain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomAuthentication implements AuthenticationProvider {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        System.out.println(userPrinciple);
        System.out.println(authentication);
        // sử dụng userDetailService để lấy thông tin user
        Optional<UserPrinciple> user = userService.getUserById(userPrinciple.getId());

        // logic xac thuc user
        UsernamePasswordAuthenticationToken result = null;
        if (user.get().getUsername().equals(userPrinciple.getUsername()) && user.get().getPassword().equals(userPrinciple.getPassword().toString())) {
            result = new UsernamePasswordAuthenticationToken(user.get().getUsername(), user.get().getPassword(), new ArrayList<GrantedAuthority>());
        }
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
