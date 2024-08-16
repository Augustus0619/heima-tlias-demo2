package com.example.heimatilaswebmanagement2demo.config;

import com.example.heimatilaswebmanagement2demo.Interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    //自定义拦截对象
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //注册自定义拦截对象
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");//设置拦截器拦截的请求路径（ /** 表示拦截所有请求） 设置不拦截的请求路径

    }
}
