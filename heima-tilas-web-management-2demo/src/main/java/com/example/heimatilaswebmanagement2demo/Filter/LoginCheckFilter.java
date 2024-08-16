package com.example.heimatilaswebmanagement2demo.Filter;

import com.alibaba.fastjson.JSONObject;
import com.example.heimatilaswebmanagement2demo.pojo.Result;
import com.example.heimatilaswebmanagement2demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")//拦截所有请求
public class LoginCheckFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        //前置：强制转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求路径：{}", url); //请求路径：http://localhost:8080/login

        //2.如果包含/login,是登陆请求，放行
        if(url.contains("/login"))
        {
            filterChain.doFilter(request,response);
            return;
        }

        //3.不是登陆请求则要校验jwt令牌
        String token = request.getHeader("token");
        log.info("从请求头中获得的令牌:{}",token);

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(token))
        {
            log.info("Token不存在");
            Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try
        {
            JwtUtils.parseJWT(token);
        }
        catch (Exception e)
        {
            log.info("令牌解析失败！");
            Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);

            return;
        }

        //6.放行
        filterChain.doFilter(request,response);
    }
}
