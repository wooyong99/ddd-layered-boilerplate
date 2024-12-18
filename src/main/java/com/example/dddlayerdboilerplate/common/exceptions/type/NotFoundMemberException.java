package com.example.dddlayerdboilerplate.common.exceptions.type;

import com.example.dddlayerdboilerplate.common.exceptions.error.ErrorCode;

public class NotFoundMemberException extends BaseException{
    public NotFoundMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
