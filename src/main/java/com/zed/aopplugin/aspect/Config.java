package com.zed.aopplugin.aspect;

import com.zed.aopplugin.run.Run1;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
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
//    @Bean
//    DynamicMethodMatcherPointcut dynamicMethodMatcherPointcut() {
//
//        return new DynamicMethodMatcherPointcut() {
//            @Override
//            public boolean matches(Method method, Class<?> targetClass, Object... args) {
//                System.out.println("********************"+method.getName());
//                return method.getName().equals("run");
//            }
//        };
//    }

    @Bean
    MethodBeforeAdvice methodBeforeAdvice() {
        return (method, args, target) -> {
            System.out.println("beforeAdvice******************");
//            System.out.println(method);
//            for (Object o: args)
//                System.out.println(o);
//            System.out.println(target);
        };
    }

    @Bean
    AfterReturningAdvice afterReturningAdvice() {
        return (returnValue, method, args, target) -> {
            System.out.println("11111111111111111111");
        };
    }

    //后置
    @Bean
    DefaultPointcutAdvisor defaultPointcutAdvisor(MyDynamicPointcut pointcut,
                                                  AfterReturningAdvice after) {
        return new DefaultPointcutAdvisor(pointcut,after);
    }

    //前置
    @Bean
    DefaultPointcutAdvisor defaultPointcutAdvisor1(MyDynamicPointcut pointcut,
                                                   MethodBeforeAdvice beforeAdvice) {
        return new DefaultPointcutAdvisor(pointcut,beforeAdvice);
    }

//    @Bean
//    ProxyFactoryBean proxyFactoryBean(DefaultPointcutAdvisor advisor,
//                                      Run1 run1) {
//        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//        proxyFactoryBean.setTarget(run1);
//        proxyFactoryBean.setInterceptorNames(advisor.toString());
//        return proxyFactoryBean;
//    }
}
