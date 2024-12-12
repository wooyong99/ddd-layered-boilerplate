package com.example.dddlayerdboilerplate.common.dto;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public sealed class ExceptionDto permits ArgumentNotValidExceptionDto {

    @JsonProperty("code")
    private final Integer code;

    @JsonProperty("message")
    private final String message;

    public ExceptionDto(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ExceptionDto(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ExceptionDto of(ErrorCode errorCode) {
        return new ExceptionDto(errorCode.getCode(), errorCode.getMessage());
    }

    public static ExceptionDto of(ErrorCode errorCode, String message) {
        return new ExceptionDto(errorCode.getCode(), message);
    }
}
