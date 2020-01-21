package com.start.test.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * 把此类加载到Spring IOC
 */
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }


    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    private static void checkApplicationContext() {
        if (null == applicationContext) {
            throw new IllegalStateException("applicaitonContext未注入");
        }
    }

    /**
     * 判断是否存在该类型的实例。
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> boolean isFactoryOf(Class<T> clazz) {
        Map<String, T> beansOfType = getApplicationContext().getBeansOfType(clazz);
        return beansOfType.size() > 0;
    }
}
