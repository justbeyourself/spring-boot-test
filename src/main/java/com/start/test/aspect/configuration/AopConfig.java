package com.start.test.aspect.configuration;

import com.alibaba.fastjson.JSONObject;
import com.start.test.dto.ChildBody;
import com.start.test.dto.ParamDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-08-16 15:27
 */
@Configuration
@Aspect
public class AopConfig {

//    @Around("@within(org.springframework.web.bind.annotation.RestController)")
//    public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable {
//        try {
//            Object[] args = pjp.getArgs();
//            System.out.println("args:" + Arrays.toString(args) + "\ncount:" + args.length);
//            Object o = pjp.proceed();
//            System.out.println("return:" + o);
//            return o;
//        } catch (Throwable e) {
//            throw e;
//        }
//    }

//    @Before("execution(* com.start.test.controller.HelloWorldController.test*())")
//    public void before() {
//        System.out.println("before check.");
//    }
//
//    @After(value = "target(com.start.test.service.TestService)")
//    public void after() {
//        System.out.println("after check.");
//    }
}
