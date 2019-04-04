package com.dennis.api.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.dennis.api.interceptor.AuthorizationInterceptor;
import com.dennis.api.interceptor.SignAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


//@EnableWebMvc   开启后 静态资源不能访问
@Configuration
public class  InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public AuthorizationInterceptor authorizationInterceptor(){
        return new AuthorizationInterceptor();
    }

    @Bean
    public SignAuthInterceptor signAuthInterceptor(){
        return new SignAuthInterceptor();
    }



    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //处理中文乱码问题
        List<MediaType> fastMediaTypeList = new ArrayList<>();
        fastMediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypeList);
        converters.add(fastConverter);
    }


    /**
     *
     * @Author: D丶Cheng
     * @Description: 添加拦截器
     * @Date: 2018/5/25 下午10:23
     *
     * @Param: [registry]
     *
     * @Return: void
     *
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authorizationInterceptor());
//        registry.addInterceptor(signAuthInterceptor())
//                .excludePathPatterns("/charge/aliNotify.action")
//                .excludePathPatterns("/charge/wxNotify.action")
//                .excludePathPatterns("/home/upload.action")
//                .excludePathPatterns("/upload/**");
    }

    

}
