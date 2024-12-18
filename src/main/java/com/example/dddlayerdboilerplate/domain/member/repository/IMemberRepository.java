package com.example.dddlayerdboilerplate.domain.member.repository;

import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.model.vo.Status;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IMemberRepository {

    Long save(MemberEntity entity);

    Optional<MemberEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    Optional<MemberEntity> findById(long id);
}
