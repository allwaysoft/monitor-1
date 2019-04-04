package com.dennis.api.exception;


import com.dennis.common.enums.ResultEnum;

/**
 * Created by chenpan on 2017/5/25.
 */
public class AccessDeniedException extends ApiException {

    public AccessDeniedException() {
        super(ResultEnum.ACCESS_DENIED.getMsg(), ResultEnum.ACCESS_DENIED.getCode());
    }

}
