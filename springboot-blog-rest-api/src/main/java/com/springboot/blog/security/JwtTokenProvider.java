package com.springboot.blog.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {


    @Value("${app.jwt-secret}")
    private String jwtSecret ;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateJwtAuthenticationToken(Authentication authentication){
        String username = authentication.getName() ;
        Date currentDate = new Date();
        Date expiry = new Date(currentDate.getTime()+jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.RS512,jwtSecret)
                .compact() ;
        return  token ;
    }

    public String getNameFromJwt(String token ) {
        Claims cliams = Jwts.parser()
                       .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return  cliams.getSubject() ;
    }
}
