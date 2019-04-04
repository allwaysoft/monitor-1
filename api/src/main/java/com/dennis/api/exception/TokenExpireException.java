package com.dennis.api.exception;


import com.dennis.common.enums.ResultEnum;

/**
 * Created by chenpan on 2017/5/23.
 */
public class TokenExpireException extends ApiException {


    public TokenExpireException() {
        super(ResultEnum.TOKEN_EXPIRE.getMsg(), ResultEnum.TOKEN_EXPIRE.getCode());
    }

    public TokenExpireException(String msg, int code) {
        super(msg, code);
    }
}
