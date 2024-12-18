package com.example.dddlayerdboilerplate.domain.member.application.mapper;

import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import com.example.dddlayerdboilerplate.domain.member.model.vo.TokenInfo;
import com.example.dddlayerdboilerplate.domain.member.ui.response.JoinResponse;
import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberMapper {

    // Domain -> Entity
    public MemberEntity toEntity(Member domain) {
        MemberEntity entity = MemberEntity.create(
                domain.getEmail(),
                domain.getPassword(),
                domain.getDescription(),
                domain.getAddress(),
                Optional.ofNullable(domain.getTokenInfo())
                        .map(TokenInfo::getRefreshToken)
                        .orElse(null),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
        if(domain.getId() != null){
            entity.setId(domain.getId());
        }
        return entity;
    }

    // Entity -> Domain
    public Member toDomain(MemberEntity entity) {
        return Member.create(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getDescription(),
                entity.getAddress(),
                new TokenInfo(null,entity.getRefreshToken()),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
