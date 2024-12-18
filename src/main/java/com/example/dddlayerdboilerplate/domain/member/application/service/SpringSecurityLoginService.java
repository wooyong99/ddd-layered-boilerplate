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

    private final AuthenticationManager authenticationManager;

    public SpringSecurityLoginService(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public Member login(LoginServiceCommand command) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(command.getEmail(), command.getPassword())
        );

        CustomDetails principal = (CustomDetails) authenticate.getPrincipal();

        return principal.getMember();
    }
}
