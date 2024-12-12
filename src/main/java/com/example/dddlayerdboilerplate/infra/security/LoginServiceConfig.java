package com.example.dddlayerdboilerplate.infra.security;

import com.example.dddlayerdboilerplate.domain.member.application.mapper.MemberMapper;
import com.example.dddlayerdboilerplate.domain.member.application.service.LoginService;
import com.example.dddlayerdboilerplate.domain.member.application.service.SpringSecurityLoginService;
import com.example.dddlayerdboilerplate.domain.member.repository.IMemberRepository;
import com.example.dddlayerdboilerplate.infra.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class LoginServiceConfig {

    @Bean
    public LoginService sringSecurityLoginService(IMemberRepository memberRepository, MemberMapper memberMapper, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        return new SpringSecurityLoginService(
                memberRepository,
                memberMapper,
                authenticationManager,
                tokenProvider);
    }

    public LoginService defaultLoginService(IMemberRepository memberRepository, MemberMapper memberMapper, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        return new SpringSecurityLoginService(
                memberRepository,
                memberMapper,
                authenticationManager,
                tokenProvider);
    }
}
