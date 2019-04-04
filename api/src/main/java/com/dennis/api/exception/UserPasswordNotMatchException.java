package com.dennis.api.exception;


import com.dennis.common.enums.ResultEnum;

public class UserPasswordNotMatchException extends ApiException{

    public UserPasswordNotMatchException() {
        super( ResultEnum.USER_PASSWORD_NOT_MATCH.getMsg(), ResultEnum.USER_PASSWORD_NOT_MATCH.getCode());
    }

}
