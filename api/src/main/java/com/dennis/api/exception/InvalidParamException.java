package com.dennis.api.exception;


import com.dennis.common.enums.ResultEnum;

/**
 * @Company: NJBandou
 * @Author: Chen Pan
 * @Description:
 * @Date: 2018/7/5 下午2:22
 */
public class InvalidParamException extends ApiException {


    public InvalidParamException() {
        super(ResultEnum.ILLEGAL_ARGUMENT.getMsg(), ResultEnum.ILLEGAL_ARGUMENT.getCode());
    }
}
