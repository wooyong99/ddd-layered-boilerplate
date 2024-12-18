package com.example.dddlayerdboilerplate.domain.member.application.service;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;
import com.example.dddlayerdboilerplate.common.exceptions.type.JoinFailException;
import com.example.dddlayerdboilerplate.domain.member.application.command.JoinServiceCommand;
import com.example.dddlayerdboilerplate.domain.member.application.mapper.MemberMapper;
import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.model.vo.Status;
import com.example.dddlayerdboilerplate.domain.member.repository.IMemberRepository;
import com.example.dddlayerdboilerplate.domain.member.service.MemberDuplicationChecker;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;
import com.example.dddlayerdboilerplate.infra.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class JoinService {

    private final IMemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final MemberMapper memberMapper;
    private final MemberDuplicationChecker memberDuplicationChecker;

    public JoinService(IMemberRepository memberRepository, PasswordEncoder encoder, MemberMapper memberMapper, MemberDuplicationChecker memberDuplicationChecker) {
        this.memberRepository = memberRepository;
        this.encoder = encoder;
        this.memberMapper = memberMapper;
        this.memberDuplicationChecker = memberDuplicationChecker;
    }

    public Member join(JoinServiceCommand command) {
        Member domain = Member.create(
                command.getEmail(),
                command.getPassword(),
                command.getDescription(),
                command.getAddress(),
                Status.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        // 1. 중복 검사
        if( memberDuplicationChecker.isExistsByEmail(domain.getEmail()) ) {
            throw new JoinFailException(ErrorCode.DUPLICATE_USER);
        };
        // 2. 비밀번호 3자리 이상 검사
        if ( !domain.validatePassword() )
        {
            throw new JoinFailException(ErrorCode.INVALID_PASSWORD_FORMAT);
        }

        // 3. 비밀번호 인코딩
        domain.setEncodedPassword(encoder.encode(command.getPassword()));

        MemberEntity entity = memberMapper.toEntity(domain);

        memberRepository.save(entity);

        return memberMapper.toDomain(entity);
    }
}


