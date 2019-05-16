package com.dennis.common.result;


import com.dennis.common.enums.ResultEnum;

public class ResultUtil {


    public static Result success() {
        return new ResultBuilder<>().success();
    }

    public static Result success(String msg) {
        return new ResultBuilder<>().setCode(0).setMsg(msg).build();
    }

    public static Result success(ResultEnum successEnum) {
        return new ResultBuilder<>().setCode(0).setMsg(successEnum.getMsg()).build();
    }

    public static Result success(String msg, Object data) {
        return new ResultBuilder<>().setCode(0).setMsg(msg).setData(data).build();
    }


    public static Result error() {
        return new ResultBuilder().error();
    }

    public static Result error(String msg) {
        return new ResultBuilder().error(msg);
    }

    public static Result error(ResultEnum errorEnum) {
        return error(errorEnum.getCode(), errorEnum.getMsg());
    }

    public static Result error(Integer code, String msg) {
        return new ResultBuilder().setCode(code).setMsg(msg).build();
    }

    public static Result error(Integer code, String msg, Object data) {
        return new ResultBuilder().setCode(code).setMsg(msg).setData(data).build();
    }


}
