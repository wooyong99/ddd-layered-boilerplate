package com.example.dddlayerdboilerplate.infra.persistence;

import com.example.dddlayerdboilerplate.common.exceptions.type.LoginFailException;
import com.example.dddlayerdboilerplate.common.exceptions.type.NotFoundMemberException;
import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.model.vo.Status;
import com.example.dddlayerdboilerplate.domain.member.repository.IMemberRepository;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode.FAILURE_LOGIN;

@Repository
@RequiredArgsConstructor
public class MemberRepository implements IMemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Long save(MemberEntity entity) {
        memberJpaRepository.save(entity);
        return entity.getId();
    }

    @Override
    public Optional<MemberEntity> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<MemberEntity> findById(long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return memberJpaRepository.existsById(id);
    }
}
