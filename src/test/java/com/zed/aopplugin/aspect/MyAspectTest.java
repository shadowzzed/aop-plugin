package com.zed.aopplugin.aspect;

import com.zed.aopplugin.MyAnnotation;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zeluo
 * @date 2019/10/18 9:15
 */
@SpringBootTest
class MyAspectTest {
    @MyAnnotation(name = "11")
    private String name;

    @Autowired
    MyAspect my;

    @Test
    public void test() throws IOException {
    }

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        Method[] methods = MyAspect.class.getDeclaredMethods();
        for (Method method: methods) {
            System.out.println("method :" + method);
            Pointcut pointcut = method.getAnnotation(Pointcut.class);
            if (pointcut != null) {
                InvocationHandler han = Proxy.getInvocationHandler(pointcut);//获取处理器
                System.out.println(han.getClass());
                Field field = han.getClass().getDeclaredField("memberValues");//获取memberValues字段
                System.out.println("field :" + field);
                field.setAccessible(true);//设置可修改
                Map memberMethods = ((Map) field.get(han));
                String value = pointcut.value();
                System.out.println("value :"+ value);
                memberMethods.put("value","111");
                System.out.println(pointcut.value());
            }
        }
    }
}