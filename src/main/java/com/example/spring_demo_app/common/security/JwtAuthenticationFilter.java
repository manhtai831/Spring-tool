package com.example.spring_demo_app.common.security;

import com.example.spring_demo_app.common.exception.AppAuthenticationException;
import com.example.spring_demo_app.domain.service.impl.JwtServiceImpl;
import com.example.spring_demo_app.domain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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


    @Autowired
    public void setJwtService(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }


    private String getJwtFromRequest(HttpServletRequest request) {
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
            if (jwt != null) {
                Long id = jwtService.getIdFromJwtToken(jwt);
                System.out.println("AAAAAAAAAA   " + id);
                Optional<UserPrinciple> userDetails = userService.getUserById(id);
                System.out.println(userDetails);

//                if (!userDetails.isEmpty()){
//                    response.sendError(99,"Khoong theer authen");
//                    filterChain.doFilter(request, response);
//                    return;
//                }

                var authentication = new UsernamePasswordAuthenticationToken(userDetails.get(), null, userDetails.get().getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        filterChain.doFilter(request, response);
    }

}
