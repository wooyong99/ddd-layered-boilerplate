package com.example.dddlayerdboilerplate.domain.member.application.command;

import com.example.dddlayerdboilerplate.common.dto.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginServiceCommand extends SelfValidating<LoginServiceCommand> {
    private String email;
    private String password;

    public LoginServiceCommand(String email,String password) {
        this.email = email;
        this.password = password;
        this.validateSelf();
    }
}
