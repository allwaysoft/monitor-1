package com.dennis.api.interceptor;


import com.dennis.api.exception.AccessDeniedException;
import com.dennis.api.exception.TokenExpireException;
import com.dennis.api.redis.TokenRedisDao;
import com.dennis.common.annotation.Authorization;
import com.dennis.common.constants.ApiConstant;
import com.dennis.common.constants.UserConstant;
import com.dennis.common.tools.JWTUtils;
import com.dennis.common.tools.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by D丶Cheng on 2017/5/25.
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    private final static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private TokenRedisDao tokenRedisDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.getAnnotation(Authorization.class) == null) {
            return true;
        }

        //从header中得到token
        String authorization = request.getHeader(ApiConstant.AUTHORIZATION);

        if (authorization == null) {
            throw new AccessDeniedException();
        }

        //验证token真伪
        Map map = JWTUtils.getClaims(ApiConstant.USER_JWT_PREFIX, authorization);
        if (map == null) {
            throw new AccessDeniedException();
        }

        // 从Redis读取token
        String userId = MapUtil.getString(map, UserConstant.PKID);
        if (!tokenRedisDao.checkToken(userId, authorization)) {
            throw new TokenExpireException();
        }

        // 设置 用户ID
        request.setAttribute(UserConstant.PKID, userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    }


}
