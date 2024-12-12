package com.example.dddlayerdboilerplate.common.constants;

import java.util.List;

public class Constants {
    // 회원 검증 메시지
    public static final String VALIDATION_MESSAGE_EMAIL_NOT_NULL_OR_BLANK = "이메일은 공백이나 NULL을 허용하지 않습니다.";
    public static final String VALIDATION_MESSAGE_EMAIL_INVALID_FORMAT = "이메일 형식이 올바르지 않습니다.";
    public static final String VALIDATION_MESSAGE_PASSWORD_NOT_NULL_OR_BLANK  = "비밀번호는 공백이나 NULL을 허용하지 않습니다.";
    public static final String VALIDATION_MESSAGE_DESCRIPTION_NOT_NULL_OR_BLANK  = "자기소개는 공백이나 NULL을 허용하지 않습니다.";
    public static final String VALIDATION_MESSAGE_ADDRESS_NOT_NULL_OR_BLANK  = "주소는 공백이나 NULL을 허용하지 않습니다.";

    // JWT
    public static final String ACCOUNT_ID_ARRTIBUTE_NAME = "ACCOUNT_ID";
    public static final String ACCOUNT_ID_CLAIM_NAME = "aid";
    public static final String ACCOUNT_ROLE_CLAIM_NAME = "role";

    // HTTP HEADER
    public static final String BEARER_PREFIX = "Bearer";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // COOKIE
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";


    // 인증이 필요 없는 URL
    public static List<String> NO_NEED_AUTH_URLS = List.of(
            // Authentication/Authorization
            "/api/sample",

            // Swagger
            "/api-docs.html",
            "/api-docs/**",
            "/swagger-ui/**",
            "/v3/**"
    );

    // Swagger 에서 사용하는 URL
    public static List<String> SWAGGER_URLS = List.of(
            "/api-docs.html",
            "/api-docs",
            "/swagger-ui"
    );
}
