package com.geekbrains.geekspring.services.logging;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Log4j2
public class OrderLoggingAspect {
    @After("execution(public void com.geekbrains.geekspring.services.OrderService.makeOrder())")
    public void logMakeOrder(JoinPoint joinPoint){
        System.out.println("Make Order: " + joinPoint.getSignature().toString());
//        log.info("Make Order: " + joinPoint.getSignature().toString());
    }
    @Before("execution(public void com.geekbrains.geekspring.services.OrderService.saveOrder())")
    public void logSaveOrder(){
        System.out.println("Logging Before Save Order");
//        log.info("Make Order: " + joinPoint.getSignature().toString());
    }
}
