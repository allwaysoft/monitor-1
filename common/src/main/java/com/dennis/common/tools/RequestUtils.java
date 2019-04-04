package com.dennis.common.tools;

import com.dennis.common.constants.UserConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dennis on 2019/3/31.
 */


public class RequestUtils {


    /**
     * 获取 用户ID
     * @return
     */
    public static Integer getPkId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer userId = Integer.valueOf(String.valueOf(request.getAttribute(UserConstant.PKID)));
        return userId;
    }

}
