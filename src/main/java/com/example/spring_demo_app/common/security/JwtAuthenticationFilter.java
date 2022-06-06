package com.example.spring_demo_app.common.security;

import com.example.spring_demo_app.domain.service.impl.JwtServiceImpl;
import com.example.spring_demo_app.domain.service.impl.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtServiceImpl jwtService;

    private UserServiceImpl userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doFilterInternal");
        String jwt = getJwtFromRequest(request);
        if (jwt != null && jwtService.validateJwtToken(jwt)) {
            Long id = jwtService.getIdFromJwtToken(jwt);

            Optional<UserDetails> userDetails = userService.getUserById(id);
            if (userDetails.isPresent()) {
                // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
                UsernamePasswordAuthenticationToken
                        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.get().getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        }
        System.out.println(jwt);
        filterChain.doFilter(request, response);
    }


    private String getJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        System.out.println("AAAAAAAAAA" + authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }

}
