package com.example.dddlayerdboilerplate.common.exceptions.type;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;

public class JoinFailException extends BaseException{
    public JoinFailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
