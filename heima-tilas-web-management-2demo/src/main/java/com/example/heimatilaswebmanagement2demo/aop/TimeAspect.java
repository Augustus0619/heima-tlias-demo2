package com.example.heimatilaswebmanagement2demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect //当前类为切面类
@Slf4j
public class TimeAspect
{
    //当切入点方法使用private修饰时，仅能在当前切面类中引用该表达式
    //切入点方法（公共的切入点表达式）
    @Pointcut("execution(* com.example.heimatilaswebmanagement2demo.service.*.*(..))")
    private void pt(){

    }
    //前置通知
    @Before("pt()")
    public void before(JoinPoint joinPoint){
        log.info("before ...");

    }
    //第一个*:匹配任意返回类型的所有方法
    //.*.*(..)：匹配 com.example.heimatilaswebmanagement2demo.service 包中的所有类，类中的所有方法名，匹配任意参数列表的所有方法
    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //记录方法执行开始时间
        long begin = System.currentTimeMillis();

        //执行原始方法
        Object result = pjp.proceed();

        //记录方法执行结束时间
        long end = System.currentTimeMillis();

        //计算方法执行耗时
        log.info(pjp.getSignature()+"执行耗时: {}毫秒",end-begin);

        return result;
    }

    //后置通知:有无异常都会
    @After("pt()")
    public void after(JoinPoint joinPoint){
        log.info("after ...");
    }

    //返回后通知（程序在正常执行的情况下，会执行的后置通知）:只有正常执行才会有通知
    @AfterReturning("pt()")
    public void afterReturning(JoinPoint joinPoint){
        log.info("afterReturning ...");
    }

    //异常通知（程序在出现异常的情况下，执行的后置通知）:只有有异常才后置通知
    @AfterThrowing("pt()")
    public void afterThrowing(JoinPoint joinPoint){
        log.info("afterThrowing ...");
    }
}
