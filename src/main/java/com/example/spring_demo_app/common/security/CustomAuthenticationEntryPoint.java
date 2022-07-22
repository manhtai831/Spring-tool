package com.example.spring_demo_app.common.security;

import com.example.spring_demo_app.common.contants.AppConstants;
import com.example.spring_demo_app.common.model.BaseResponse;
import com.example.spring_demo_app.common.model.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(response.getOutputStream(), BaseResponse.error(Error.custom(AppConstants.Code.UNAUTHORIZED, "UnAuthentication")));
    }
}
