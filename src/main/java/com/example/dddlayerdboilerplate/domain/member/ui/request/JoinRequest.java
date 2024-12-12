package com.example.dddlayerdboilerplate.domain.member.ui.request;

import com.example.dddlayerdboilerplate.common.dto.SelfValidating;
import com.example.dddlayerdboilerplate.domain.member.ui.response.JoinResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JoinRequest extends SelfValidating<JoinRequest> {

    private String email;

    private String password;

    private String description;

    private String address;

    public JoinRequest(String email, String password, String description, String address) {
        this.email = email;
        this.password = password;
        this.description = description;
        this.address = address;
        this.validateSelf();
    }
}
