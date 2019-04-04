package com.dennis.common.result;


public class ResultUtil {


    public static Result success(){
        return new ResultBuilder<>().success();
    }

    public static Result success(String msg){
        return new ResultBuilder<>().setCode(0).setMsg(msg).build();
    }

    public static Result success(String msg, Object data){
        return new ResultBuilder<>().setCode(0).setMsg(msg).setData(data).build();
    }


    public static Result error(){
        return new ResultBuilder().error();
    }

    public static Result error(String msg){
        return new ResultBuilder().error(msg);
    }

    public static Result error(Integer code,String msg){
        return new ResultBuilder().setCode(code).setMsg(msg).build();
    }

    public static Result error(Integer code, String msg, Object data){
        return new ResultBuilder().setCode(code).setMsg(msg).setData(data).build();
    }


}
