package com.example.spring_demo_app.common.security;

import com.example.spring_demo_app.common.exception.AppAuthenticationException;
import com.example.spring_demo_app.data.model.UserModel;
import com.example.spring_demo_app.domain.service.impl.JwtServiceImpl;
import com.example.spring_demo_app.domain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private UserServiceImpl userService;


    private String getJwtFromRequest(HttpServletRequest request) throws AppAuthenticationException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) return null;

        if (authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try {
            String jwt = getJwtFromRequest(request);
            if (jwtService.validateJwtToken(jwt)) {
                Long id = jwtService.getIdFromJwtToken(jwt);

                UserModel userDetails = userService.getUserById(id);

                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        filterChain.doFilter(request, response);
    }

}
