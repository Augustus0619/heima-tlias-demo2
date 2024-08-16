package com.example.heimatilaswebmanagement2demo.Filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter(urlPatterns = "/login") //  /* 表示拦截浏览器的所有请求
public class DemoFilter implements Filter
{//初始化方法, 只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        System.out.println("init 初始化方法执行了");
        Filter.super.init(filterConfig);
    }

    @Override //拦截到请求之后调用, 调用多次
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        System.out.println("Demo 拦截到了请求...放行前逻辑");
        //放行
        chain.doFilter(request,response);

        System.out.println("DemoFilter   放行后逻辑.....");
    }

    @Override //销毁方法, 只调用一次
    public void destroy()
    {
        System.out.println("destroy 销毁方法执行了");
        Filter.super.destroy();
    }
}
