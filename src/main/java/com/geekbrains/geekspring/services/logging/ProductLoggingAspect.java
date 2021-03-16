package com.geekbrains.geekspring.services.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
//@Log4j2
public class ProductLoggingAspect {
    private MassageLog massageLog;

    @Pointcut("execution(public * com.geekbrains.geekspring.services.ProductService.*All*(..))") // pointcut expression
    public void productGetAllTrackerPointcut() {
    }

    @Pointcut("execution(public * com.geekbrains.geekspring.services.ProductService.*With*(..))") // pointcut expression
    public void productGetWithTrackerPointcut() {
    }

    @Pointcut("productGetAllTrackerPointcut() || productGetWithTrackerPointcut()") // pointcut expression
    public void productGetAllOrWithTrackerPointcut() {
    }

    @After("productGetAllOrWithTrackerPointcut()")
    public void logMethodOrder(JoinPoint joinPoint) {
        getMassageLogProduct(joinPoint, "Args", "Method");
    }

    @After("execution(public * com.geekbrains.geekspring.services.ProductService.saveProduct(..)))")
    public void logFindAllOrders(JoinPoint joinPoint) {
        getMassageLogProduct(joinPoint, "Save product", "from method");
    }

    @After("execution(public * com.geekbrains.geekspring.services.ProductService.getProductById(..))")
    public void logChangeOrderStatus(JoinPoint joinPoint) {
        getMassageLogProduct(joinPoint, "Find product by id", "from method");
    }

    @AfterThrowing(pointcut = "execution (public * com.geekbrains.geekspring.services.ProductService.*())",
            throwing = "exception")
    public void excInMethodsOrder(JoinPoint joinPoint, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        massageLog = new MassageLog(exception, methodSignature);
        massageLog.getMassageExceptionLog();
    }

    private void getMassageLogProduct(JoinPoint joinPoint, String massageArgs, String massageMethod) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        massageLog = new MassageLog(massageArgs, massageMethod, args, methodSignature);
        massageLog.getMassageLog();
    }
}
