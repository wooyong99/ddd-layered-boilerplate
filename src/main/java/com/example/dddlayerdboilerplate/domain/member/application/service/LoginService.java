package com.example.dddlayerdboilerplate.domain.member.application.service;

import com.example.dddlayerdboilerplate.domain.member.application.command.LoginServiceCommand;
import com.example.dddlayerdboilerplate.domain.member.model.aggregate.Member;
import org.springframework.stereotype.Service;


public interface LoginService {
    Member login(LoginServiceCommand command);
}
