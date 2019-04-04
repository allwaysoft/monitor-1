package com.dennis.api.aop;

import com.alibaba.fastjson.JSON;
import com.dennis.common.tools.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Created by Dennis on 2018/7/4.
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    //切入点
    @Pointcut("execution(public * com.dennis.api.controller.*.*(..))")
    private void auth() {

    }

    @Before("auth()")
    private void before(JoinPoint joinPoint) throws Exception {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("-------------------------------------------- Request----------------------------------------------");
        // url
        logger.info("URL = {} ", request.getRequestURL());
        // method
        logger.info("METHOD = {}", request.getMethod());
        // ip
        logger.info("IP = {}", getIp(request));
        // 类名 、 类方法
        logger.info("ClASS_METHOD = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // 参数
        logger.info("ARGS = {}", showParamter(request.getParameterMap()));
        // 时间
        logger.info("REQUEST_DATE = {}", DateUtil.getNowDateHM());

        logger.info("---------------------------------------------Request----------------------------------------------");
    }


    @AfterReturning(value = "auth()", returning = "rvt")
    private void after(JoinPoint joinPoint, Object rvt) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        logger.info("--------------------------------------------Response----------------------------------------------");

        // 响应方法
        logger.info("ClASS_METHOD = {}.{}", method.getDeclaringClass().getName(), method.getName());

        // 响应数据
        logger.info("RESPONSE_DATA = {}", JSON.toJSON(rvt));

        // 时间
        logger.info("RESPONSE_DATE = {}", DateUtil.getNowDateHM());

        logger.info("--------------------------------------------Response----------------------------------------------");

    }


    public String showParamter(Map<String, String[]> map) {
        String result = "";
        for (String key : map.keySet()) {
            result += key + ":" + String.valueOf(map.get(key)[0]) + " ";
        }
        return result;
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

}