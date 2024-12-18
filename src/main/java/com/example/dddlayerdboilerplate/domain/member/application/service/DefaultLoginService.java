package com.example.dddlayerdboilerplate.domain.member.application.service;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;
import com.example.dddlayerdboilerplate.common.exceptions.type.LoginFailException;
import com.example.dddlayerdboilerplate.domain.member.application.command.LoginServiceCommand;
import com.example.dddlayerdboilerplate.domain.member.application.mapper.MemberMapper;
import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.model.vo.TokenInfo;
import com.example.dddlayerdboilerplate.domain.member.repository.IMemberRepository;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;
import com.example.dddlayerdboilerplate.infra.security.jwt.TokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class DefaultLoginService implements LoginService {

    private final IMemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    public DefaultLoginService(IMemberRepository memberRepository, MemberMapper memberMapper, PasswordEncoder encoder, TokenProvider tokenProvider) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.encoder = encoder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    @Override
    public Member login(LoginServiceCommand command) {
        // 1. 존재하는 회원인지 확인 (이메일로 확인) -> 엔티티 객체 조회
        // - 엔티티 조회
        // - 도메인 모델로 변환
        MemberEntity entity = memberRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new LoginFailException(ErrorCode.FAILURE_LOGIN));
        Member domain = memberMapper.toDomain(entity);

        // 2. 비밀번호가 올바른지 확인
        if(!encoder.matches(command.getPassword(), domain.getPassword()) ) {
            throw new LoginFailException(ErrorCode.FAILURE_LOGIN);
        };
        // 3. 액세스, 리프레쉬 토큰 생성
        String accessToken = tokenProvider.createAccessToken(String.valueOf(domain.getId()));
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(domain.getId()));

        // 4. 리프레쉬 토큰 저장 및 도메인 모델 엔티티 동기환
        domain.setTokenInfo(new TokenInfo(accessToken, refreshToken));
        MemberEntity updateEntity = memberMapper.toEntity(domain);
        entity.merge(updateEntity);

        // 5. 저장
        Long save = memberRepository.save(entity);

        // 6. 반환
        domain = memberMapper.toDomain(entity);
        return domain;
    }
}
