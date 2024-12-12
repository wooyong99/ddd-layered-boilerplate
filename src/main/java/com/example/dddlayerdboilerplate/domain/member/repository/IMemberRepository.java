package com.example.dddlayerdboilerplate.domain.member.repository;

import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.model.vo.Status;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;

import java.time.LocalDateTime;

public interface IMemberRepository {

    Long save(MemberEntity entity);

    MemberEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
