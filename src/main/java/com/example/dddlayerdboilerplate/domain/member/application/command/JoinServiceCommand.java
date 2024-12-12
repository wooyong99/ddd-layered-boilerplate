package com.example.dddlayerdboilerplate.domain.member.application.command;

import com.example.dddlayerdboilerplate.common.dto.SelfValidating;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JoinServiceCommand extends SelfValidating<JoinServiceCommand> {

    private String email;

    private String password;

    private String description;

    private String address;

    public JoinServiceCommand(String email, String password, String description, String address) {
        this.email = email;
        this.password = password;
        this.description = description;
        this.address = address;
    }
}
