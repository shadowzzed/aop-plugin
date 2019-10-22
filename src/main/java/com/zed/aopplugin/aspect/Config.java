package com.zed.aopplugin.aspect;

import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @author Zeluo
 * @date 2019/10/22 14:20
 */
@Configuration
public class Config {
    @Bean
    DynamicMethodMatcherPointcut dynamicMethodMatcherPointcut() {
        return new DynamicMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                System.out.println("********************"+method.getName());
                return method.getName().equals("run");
            }
        };
    }
}
