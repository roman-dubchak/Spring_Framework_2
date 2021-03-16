package com.geekbrains.geekspring.services.logging;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.Date;

@Data
public class MassageLog {
    private String massageArgs;
    private String massageMethod;
    private Object[] args;
    private MethodSignature methodSignature;
    private Throwable exception;

    public MassageLog(Throwable exception, MethodSignature methodSignature) {
        this.methodSignature = methodSignature;
        this.exception = exception;
    }

    public MassageLog(String massageArgs, String massageMethod, Object[] args, MethodSignature methodSignature) {
        this.massageArgs = massageArgs;
        this.massageMethod = massageMethod;
        this.args = args;
        this.methodSignature = methodSignature;
    }

    public void getMassageLog() {
        System.out.printf("%tF  %tT %s: %s; %s: %s.",
                new Date(), new Date(), massageArgs, Arrays.toString(args), massageMethod, methodSignature);
        System.out.println();
    }

    public void getMassageExceptionLog() {
        System.out.printf("Exception: %s in method: %s", exception.getMessage(), methodSignature);
        System.out.println();
    }
}
