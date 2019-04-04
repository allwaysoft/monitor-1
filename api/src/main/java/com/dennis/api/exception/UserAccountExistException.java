package com.dennis.api.exception;


import com.dennis.common.enums.ResultEnum;

public class UserAccountExistException extends ApiException {

    public UserAccountExistException() {
        super(ResultEnum.ACCOUNT_EXIST.getMsg(), ResultEnum.ACCOUNT_EXIST.getCode());
    }

    public UserAccountExistException(String msg, int code) {
        super(msg, code);
    }

}
