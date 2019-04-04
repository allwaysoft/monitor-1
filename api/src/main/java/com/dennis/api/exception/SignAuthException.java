package com.dennis.api.exception;


import com.dennis.common.enums.ResultEnum;

/**
 * Created by Mr.Dxs on 2018/10/29.
 */
public class SignAuthException extends ApiException {

    public SignAuthException() {
        super(ResultEnum.SIGN_AUTH_NOT_MATCH.getMsg(), ResultEnum.SIGN_AUTH_NOT_MATCH.getCode());
    }
}
