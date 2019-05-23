package com.example.reservation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergii Biliaiev
 * Created on 22/05/2019.
 */
@Aspect
public class LoggableAspect {
    private final Logger log;

    public LoggableAspect(String loggerName) {
        super();
        log = LoggerFactory.getLogger(loggerName);
    }

    @Pointcut("@annotation(Timer)")
    public void executedTime() {

    }

    @Around("executedTime()")
    public Object logTimingMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceedPoint = proceedingJoinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;

        log.info(String.format("Method: %s | %d ms", proceedingJoinPoint.getSignature().getName(), totalTime));

        return proceedPoint;
    }
}
