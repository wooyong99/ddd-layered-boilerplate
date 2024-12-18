package com.example.dddlayerdboilerplate.infra.security;

import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter
public class CustomDetails implements UserDetails {

    private Member member;
    private String accessToken;
    private String refreshToken;

    public CustomDetails(Member member, String accessToken, String refreshToken) {
        this.member = member;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // 계정 권한 목록 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // 비밀번호 반환
    @Override
    public String getPassword() {
        return member.getPassword();
    }
    
    // 이메일 반환
    public String getEmail(){
        return member.getEmail();
    }

    // 이메일 반환
    @Override
    public String getUsername() {
        return String.valueOf(member.getId());
    }
}
