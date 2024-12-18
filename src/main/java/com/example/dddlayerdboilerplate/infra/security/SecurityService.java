package com.example.dddlayerdboilerplate.infra.security;

import org.springframework.security.core.Authentication;

public interface SecurityService {
    Authentication getAuthentication(String memberId);
}
