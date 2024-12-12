package com.example.dddlayerdboilerplate.domain.member.application.service;

import com.example.dddlayerdboilerplate.domain.member.application.command.LoginServiceCommand;
import com.example.dddlayerdboilerplate.domain.member.application.mapper.MemberMapper;
import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.repository.IMemberRepository;
import com.example.dddlayerdboilerplate.infra.security.CustomDetails;
import com.example.dddlayerdboilerplate.infra.security.jwt.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

public class SpringSecurityLoginService implements LoginService {

    private final IMemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public SpringSecurityLoginService(IMemberRepository memberRepository, MemberMapper memberMapper, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @Transactional
    public String login(LoginServiceCommand command) {
        System.out.println("스프링 시큐리티");
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(command.getEmail(), command.getPassword())
        );
        CustomDetails principal = (CustomDetails) authenticate.getPrincipal();
        return principal.getAccessToken();
    }
}
