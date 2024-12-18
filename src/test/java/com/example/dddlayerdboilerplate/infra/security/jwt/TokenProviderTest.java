package com.example.dddlayerdboilerplate.infra.security.jwt;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("TokenProvider 테스트")
class TokenProviderTest {

    @Autowired
    TokenProvider tokenProvider;

    @Test
    void 토큰생성() {
        String token = null;
        token = tokenProvider.createAccessToken("1");

        Assertions.assertNotNull(token);
    }

    @Test
    void 토큰유효성검사(){
        String token = tokenProvider.createAccessToken("1");

        Assertions.assertNotNull(tokenProvider.getClaims(token));
        Assertions.assertNotNull(tokenProvider.validateToken(token));
    }

    @Test
    void 토큰일치검사() {
        String token = tokenProvider.createAccessToken("1");
        Assertions.assertEquals(tokenProvider.getSubject(token), "1");
    }
    
    

}