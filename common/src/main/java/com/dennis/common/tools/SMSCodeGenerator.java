package com.dennis.common.tools;

import java.util.Random;

/**
 * @Author: D丶Cheng
 * @Description:
 * @Date: 2018/7/10 下午6:18
 */
public class SMSCodeGenerator {

    //默认生成6位
    public static String generate(){
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }


}
