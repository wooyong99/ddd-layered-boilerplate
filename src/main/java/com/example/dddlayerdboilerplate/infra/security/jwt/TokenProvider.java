package com.example.dddlayerdboilerplate.infra.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {

    private final JwtProperties properties;
    private Key key;

    public TokenProvider(JwtProperties properties){
        this.properties = properties;
    }

    @PostConstruct
    void init() {
        this.key = Keys.hmacShaKeyFor(properties.getSecretKey().getBytes());
    }

    public String createAccessToken(String subject) {
        return createToken(subject, properties.getAccessTokenExpiration());
    }

    public String createRefreshToken(String subject) {
        return createToken(subject, properties.getRefreshTokenExpiration());
    }

    private String createToken(String subject, long millis) {
        Claims claims = Jwts.claims().setSubject(subject);
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + millis + 1000L);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .setSubject(subject)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
