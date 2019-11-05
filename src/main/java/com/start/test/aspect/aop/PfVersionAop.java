package com.start.test.aspect.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 */
@Aspect
@Component
@Slf4j
public class PfVersionAop {
    @Before("@annotation(com.start.test.aspect.aop.PfVersion)")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        PfVersion version = method.getAnnotation(PfVersion.class);
        log.info("[{}] test start.", version.value());
        ThreadLocalAttrsUtils.setPfVersion(PfVersionEnum.STORE.getCode());
    }


    @After("@annotation(com.start.test.aspect.aop.PfVersion)")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        PfVersion version = method.getAnnotation(PfVersion.class);
        log.info("[{}] test end.", version.value());
        ThreadLocalAttrsUtils.remove();
    }
}
