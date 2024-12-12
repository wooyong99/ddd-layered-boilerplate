package com.example.dddlayerdboilerplate.common.exceptions.type;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;

public class LoginFailException extends BaseException {
    public LoginFailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
