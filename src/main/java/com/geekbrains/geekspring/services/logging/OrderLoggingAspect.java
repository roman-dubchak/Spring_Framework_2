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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

@Aspect
@Component
//@Log4j2
public class OrderLoggingAspect {
    @After("execution(public * com.geekbrains.geekspring.services.OrderService.getAllOrders())")
    public void logFindAllOrders(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.printf("Find orders: %s; from method: %s", Arrays.toString(args), methodSignature);
        System.out.println();
    }
    @After("execution(public * com.geekbrains.geekspring.services.OrderService.changeOrderStatus(..))")
    public void logChangeOrderStatus(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.printf("Order: %s; Change Status: %s", methodSignature.getReturnType().getName(), Arrays.toString(args));
        System.out.println();
    }

    @After("execution(public * com.geekbrains.geekspring.services.ProductService.*Order(..))")
    public void logMethodOrder(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.printf("%tF  %tT Method: %s; Args: %s.",
                        new Date(), new Date(), methodSignature, Arrays.toString(args));
        System.out.println();
    }

    @AfterThrowing(pointcut = "execution (public * com.geekbrains.geekspring.services.OrderService.*())",
                    throwing = "exception")
    public void excInMethodsOrder(JoinPoint joinPoint, Throwable exception){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.printf("Exception: %s in method: %s", exception.getMessage(), methodSignature);
        System.out.println();
    }
}
