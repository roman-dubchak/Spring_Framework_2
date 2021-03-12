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
//    @After("execution(public * com.geekbrains.geekspring.services.OrderService.makeOrder(..))")
//    public void logMakeOrder(JoinPoint joinPoint){
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Object[] args = joinPoint.getArgs();
//        if(args.length > 0 ) {
//            for (Object o : args) {
//                System.out.println("Args: " + o.toString());
//            }
//        }
//        System.out.println();
//        System.out.printf("Method: %s; Args: %s", methodSignature, Arrays.toString(args));
////        System.out.println(Arrays.toString(args));
////        log.info("Make Order: " + joinPoint.getSignature().toString());
//    }
//    @After("execution(public * com.geekbrains.geekspring.services.OrderService.saveOrder(..))")
//    public void logSaveOrder(JoinPoint joinPoint){
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
////        System.out.println("Save Order joinPoint.getTarget(): " + joinPoint.getTarget());
////        System.out.println("Save Order" + joinPoint.getArgs());
//        Object[] args = joinPoint.getArgs();
//        System.out.printf("Method: %s; Args: %s", methodSignature, Arrays.toString(args));
////        log.info("Make Order: " + joinPoint.getSignature().toString());
//    }

    @After("execution(public * com.geekbrains.geekspring.services.OrderService.*Order(..))")
    public void logMethodOrder(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.printf(" %tF  %tT Method: %s; Args: %s.",
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
