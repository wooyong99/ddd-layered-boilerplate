package com.example.dddlayerdboilerplate.infra.security;

import com.example.dddlayerdboilerplate.infra.persistence.entity.MemberEntity;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

@Getter
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

    private MemberEntity member;

    public CustomAuthentication(MemberEntity member) {
        super(member, null, null);
        this.member = member;
    }

    @Override
    public String getName(){
        return String.valueOf(member.getId());
    }
}
