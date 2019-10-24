package com.zed.aopplugin.aspect;

import com.zed.aopplugin.ConstentConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态切点 某个包下全部类的全部方法都要拦截
 * @author Zeluo
 * @date 2019/10/23 10:34
 */
@Component
@Slf4j
public class MyDynamicPointcut extends DynamicMethodMatcherPointcut {

    @Autowired
    ConstentConfig config;

    /**
     * 检查类
     * @return
     */
    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            log.info("正在检查 {}, 切点 {}" ,clazz.getName(),config.pointcut);
            int index = config.pointcut.length();
            //判断是不是想要的切点 判断指定包后一位是不是. 这样可以防止run run1同时拦截的情况 也可以拦截子目录比如 run.run1.run2
            if (clazz.getName().charAt(index) == '.') {
                    log.info("通过的类：{}", clazz.getName());
                    return true;
                }
            return false;
        };
    }

    /**
     * 检查方法
     * @param method
     * @param targetClass
     * @param args
     * @return
     */

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return true;
    }
}
