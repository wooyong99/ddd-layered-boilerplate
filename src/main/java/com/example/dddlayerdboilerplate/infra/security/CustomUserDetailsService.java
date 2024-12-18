package com.example.dddlayerdboilerplate.infra.security;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;
import com.example.dddlayerdboilerplate.common.exceptions.type.LoginFailException;
import com.example.dddlayerdboilerplate.domain.member.application.mapper.MemberMapper;
import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.model.vo.TokenInfo;
import com.example.dddlayerdboilerplate.infra.persistence.MemberRepository;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;
import com.example.dddlayerdboilerplate.infra.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final TokenProvider tokenProvider;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity entity = memberRepository.findByEmail(username)
                .orElseThrow(() -> new LoginFailException(ErrorCode.FAILURE_LOGIN));
        Member domain = memberMapper.toDomain(entity);
        if(domain.isWithDraw()){
            throw new LoginFailException(ErrorCode.WITHDRAW_USER);
        };

        String accessToken = tokenProvider.createAccessToken(String.valueOf(domain.getId()));
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(domain.getId()));

        domain.setTokenInfo(new TokenInfo(accessToken, refreshToken));

        entity.merge(memberMapper.toEntity(domain));
        memberRepository.save(entity);

        return new CustomDetails(domain, accessToken, refreshToken);
    }
}
