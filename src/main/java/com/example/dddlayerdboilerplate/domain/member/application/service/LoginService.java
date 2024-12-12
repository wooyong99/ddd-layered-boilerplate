package com.example.dddlayerdboilerplate.domain.member.application.service;

import com.example.dddlayerdboilerplate.domain.member.application.command.LoginServiceCommand;
import org.springframework.stereotype.Service;


public interface LoginService {
    String login(LoginServiceCommand command);
}
