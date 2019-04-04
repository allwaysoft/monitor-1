package com.dennis.api.exception;


import com.dennis.common.enums.ResultEnum;

/**
 * Created by chenpan on 2017/5/24.
 */
public class UserNotFoundException extends ApiException {

    public UserNotFoundException() {
        super(ResultEnum.USER_NOT_FOUND.getMsg(), ResultEnum.USER_NOT_FOUND.getCode());
    }

}
