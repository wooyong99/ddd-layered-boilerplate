package com.example.dddlayerdboilerplate.infra.security;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;
import com.example.dddlayerdboilerplate.common.exceptions.type.NotFoundMemberException;
import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.repository.IMemberRepository;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class SecurityServiceImpl implements SecurityService {

    private final IMemberRepository memberRepository;

    @Autowired
    public SecurityServiceImpl(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Authentication getAuthentication(String memberId) {
        MemberEntity member = getMember(memberId);

        return createAuthentication(member);
    }

    private Authentication createAuthentication(MemberEntity member) {
        return new CustomAuthentication(member);
    }

    private MemberEntity getMember(String memberId) {
        long id = Long.parseLong(memberId);

        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException(ErrorCode.NOT_LOGIN_USER));
    }
}
