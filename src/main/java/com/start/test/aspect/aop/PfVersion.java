package com.start.test.aspect.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hhh <hushunting@startdt.com>
 * @date 2019/10/12 11:54 上午
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PfVersion {

    String value() default "";

}
