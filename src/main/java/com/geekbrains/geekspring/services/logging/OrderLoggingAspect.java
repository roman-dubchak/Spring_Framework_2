package com.geekbrains.geekspring.services.logging;

import javafx.beans.binding.ObjectExpression;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Log4j2
public class OrderLoggingAspect {
    private MassageLog massageLog;

    @After("execution(public * com.geekbrains.geekspring.services.OrderService.getAllOrders())")
    public void logFindAllOrders(JoinPoint joinPoint) {
        getMassageLogOrder(joinPoint, "Find orders", "from method");
    }

    @After("execution(public * com.geekbrains.geekspring.services.OrderService.changeOrderStatus(..))")
    public void logChangeOrderStatus(JoinPoint joinPoint) {
        getMassageLogOrder(joinPoint, "Change Status", "Order");
    }

    @After("execution(public * com.geekbrains.geekspring.services.OrderService.makeOrder(..))")
    public void logMakeMethodOrder(JoinPoint joinPoint) {
        getMassageLogOrder(joinPoint, "Make Order", "Method");
    }

    @After("execution(public * com.geekbrains.geekspring.services.OrderService.saveOrder(..))")
    public void logSaveMethodOrder(JoinPoint joinPoint) {
        getMassageLogOrder(joinPoint, "Save Order", "Method");
    }

    @AfterThrowing(pointcut = "execution (public * com.geekbrains.geekspring.services.OrderService.*())",
            throwing = "exception")
    public void excInMethodsOrder(JoinPoint joinPoint, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        massageLog = new MassageLog(exception, methodSignature);
        massageLog.getMassageExceptionLog();
    }

    private void getMassageLogOrder(JoinPoint joinPoint, String massageArgs, String massageMethod) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        massageLog = new MassageLog(massageArgs, massageMethod, args, methodSignature);
        massageLog.getMassageLog();
    }
}
