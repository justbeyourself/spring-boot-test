package com.start.test.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: ${description}
 * @author: zhanghuiyong
 * @create: 2019-09-26 20:52
 */
@Configuration
public class WebMvcConfiger implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestHandlerInterceptor()).addPathPatterns("/test/**");
    }
}
