package com.example.dddlayerdboilerplate.domain.member.model.aggregate;

import com.example.dddlayerdboilerplate.common.constants.Constants;
import com.example.dddlayerdboilerplate.domain.member.model.vo.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("회원 도메인 객체 테스트")
class MemberTest {
    
    @Test
    void 회원도메인_생성_필수값_공백(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Member.create(
                    "",
                    "",
                    "",
                    "",
                    Status.ACTIVE,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
        });

        assertTrue(exception.getMessage().contains(Constants.VALIDATION_MESSAGE_EMAIL_NOT_NULL_OR_BLANK));
        assertTrue(exception.getMessage().contains(Constants.VALIDATION_MESSAGE_PASSWORD_NOT_NULL_OR_BLANK));
        assertTrue(exception.getMessage().contains(Constants.VALIDATION_MESSAGE_DESCRIPTION_NOT_NULL_OR_BLANK));
        assertTrue(exception.getMessage().contains(Constants.VALIDATION_MESSAGE_ADDRESS_NOT_NULL_OR_BLANK));
    }

    @Test
    void 회원도메인_생성_필수값_NULL(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Member.create(
                    null,
                    null,
                    null,
                    null,
                    Status.ACTIVE,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
        });

        assertTrue(exception.getMessage().contains(Constants.VALIDATION_MESSAGE_EMAIL_NOT_NULL_OR_BLANK));
        assertTrue(exception.getMessage().contains(Constants.VALIDATION_MESSAGE_PASSWORD_NOT_NULL_OR_BLANK));
        assertTrue(exception.getMessage().contains(Constants.VALIDATION_MESSAGE_DESCRIPTION_NOT_NULL_OR_BLANK));
        assertTrue(exception.getMessage().contains(Constants.VALIDATION_MESSAGE_ADDRESS_NOT_NULL_OR_BLANK));
    }

    @Test
    void 회원도메인_생성_이메일_형식(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Member.create(
                    "이메일불일치",
                    "테스트비밀번호",
                    "테스트설명",
                    "테스트주소",
                    Status.ACTIVE,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
        },Constants.VALIDATION_MESSAGE_EMAIL_INVALID_FORMAT);
    }

}