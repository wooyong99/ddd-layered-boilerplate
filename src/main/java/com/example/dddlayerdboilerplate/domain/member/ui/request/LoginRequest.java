package com.example.dddlayerdboilerplate.domain.member.ui.request;

import com.example.dddlayerdboilerplate.common.dto.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends SelfValidating<LoginRequest> {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.validateSelf();
    }
}
