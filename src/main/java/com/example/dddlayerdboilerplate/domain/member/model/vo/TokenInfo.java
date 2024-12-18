package com.example.dddlayerdboilerplate.domain.member.model.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    private String accessToken;

    private String refreshToken;
}
