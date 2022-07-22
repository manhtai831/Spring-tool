package com.example.spring_demo_app.domain.service.impl;

import com.example.spring_demo_app.domain.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;


@Component
@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String generateTokenLogin(Long id) {
        return Jwts.builder()
                .setId(String.valueOf(id))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    @Override
    public Long getIdFromJwtToken(String token) {
        String id = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getId();
        return  Long.parseLong(id);
    }

    @Override
    public boolean validateJwtToken(String authToken) {
        if(authToken == null) return false;
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
