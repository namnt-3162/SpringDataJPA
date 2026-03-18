package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.WalletService.*(..))")
    public void logBeforeTransaction(JoinPoint joinPoint) {
        System.out.println("==> AOP LOG: starting transaction: " 
            + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.service.WalletService.deposit(..))")
    public void logAfterSuccess() {
        System.out.println("==> AOP LOG: transaction successful!");
    }
}
