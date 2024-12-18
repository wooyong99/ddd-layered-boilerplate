package com.example.dddlayerdboilerplate.infra.persistence.entity;

import com.example.dddlayerdboilerplate.common.constants.Constants;
import com.example.dddlayerdboilerplate.common.dto.SelfValidating;
import com.example.dddlayerdboilerplate.domain.member.model.vo.Status;
import com.example.dddlayerdboilerplate.domain.member.model.vo.TokenInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_description")
    private String description;

    @Column(name = "user_addr")
    private String address;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private Status status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private MemberEntity(String email, String password, String description, String address, String refreshToken, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.email = email;
        this.password = password;
        this.description = description;
        this.address = address;
        this.refreshToken  = refreshToken;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static MemberEntity create(String email, String password, String description, String address, String refreshToken, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new MemberEntity(
                email,
                password,
                description,
                address,
                refreshToken,
                status,
                createdAt,
                updatedAt
        );
    }

    public void setId(Long id) {
        if(id != null){
            this.id = id;
        }
    }

    public void merge(MemberEntity updatedEntity) {
        if(Objects.equals(this.id, updatedEntity.getId())){
            this.description = updatedEntity.getDescription();
            this.address = updatedEntity.getAddress();
            this.refreshToken  = updatedEntity.getRefreshToken();
        }
    }
}
