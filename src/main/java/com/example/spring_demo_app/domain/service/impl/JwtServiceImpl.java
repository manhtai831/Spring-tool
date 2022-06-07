package com.example.spring_demo_app.domain.service.impl;

import com.example.spring_demo_app.common.security.UserPrinciple;
import com.example.spring_demo_app.domain.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;


@Component
@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String generateTokenLogin(Long id) {
//        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(id.toString())
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
                .getBody().getSubject();
        System.out.println("MMMMMMMMMMMMM" + id);
        return  Long.parseLong(id);
    }

    @Override
    public boolean validateJwtToken(String authToken) {
        System.out.println("MMMMMMMMMMMMM" + authToken);
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
