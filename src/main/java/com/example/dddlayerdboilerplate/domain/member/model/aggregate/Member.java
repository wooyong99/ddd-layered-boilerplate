package com.example.dddlayerdboilerplate.domain.member.model.aggregate;

import com.example.dddlayerdboilerplate.common.constants.Constants;
import com.example.dddlayerdboilerplate.common.dto.SelfValidating;
import com.example.dddlayerdboilerplate.domain.member.model.vo.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends SelfValidating<Member> {

    private Long id;

    @Email(message = Constants.VALIDATION_MESSAGE_EMAIL_INVALID_FORMAT)
    @NotBlank(message = Constants.VALIDATION_MESSAGE_EMAIL_NOT_NULL_OR_BLANK)
    private final String email;

    @NotBlank(message = Constants.VALIDATION_MESSAGE_PASSWORD_NOT_NULL_OR_BLANK)
    private final String password;

    @NotBlank(message = Constants.VALIDATION_MESSAGE_DESCRIPTION_NOT_NULL_OR_BLANK)
    private final String description;

    @NotBlank(message = Constants.VALIDATION_MESSAGE_ADDRESS_NOT_NULL_OR_BLANK)
    private final String address;

    private final Status status;

    private String refreshToken;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    // 생성 시점 생성자
    private Member(String email, String password, String description, String address, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.email = email;
        this.password = password;
        this.description = description;
        this.address = address;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.validateSelf();
    }
    // 리포지토리 조회 시점 생성자
    private Member(Long id, String email, String password, String description, String address, String refreshToken, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.description = description;
        this.address = address;
        this.refreshToken = refreshToken;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.validateSelf();
    }

    public static Member create(String email, String password, String description, String address, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Member(email, password, description, address, status, createdAt, updatedAt);
    }

    public static Member create(Long id, String email, String password, String description, String address, String refreshToken, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Member(id, email, password, description, address, refreshToken, status, createdAt, updatedAt);
    }

    public void setId(Long id) {
        if( id != null ){
            this.id = id;
        }
    }

    public void saveRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isWithDraw() {
        return status == Status.DELETED;
    }
}
