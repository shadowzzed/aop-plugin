package com.zed.aopplugin.aspect;

import com.zed.aopplugin.ConstentConfig;
import com.zed.aopplugin.MyAnnotation;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Zeluo
 * @date 2019/10/17 17:00
 */
@Aspect
@Component
public class MyAspect {

    private static List<String> list = new ArrayList<>();

    private static final String str_static = "execution(public * com.zed.aopplugin..*.*(..))";

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream(ConstentConfig.FILEPATH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String str = null;
            while ((str = reader.readLine()) != null)
                list.add(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @PostConstruct
    @SuppressWarnings("unchecked")
    public void beforeAspect() throws NoSuchFieldException, IllegalAccessException {
        Method[] methods = MyAspect.class.getDeclaredMethods();
        for (Method method: methods) {
            Pointcut pointcut = method.getAnnotation(Pointcut.class);
            if (pointcut != null) {
                InvocationHandler handler = Proxy.getInvocationHandler(pointcut);
                Field memberValues = handler.getClass().getDeclaredField("memberValues");
                memberValues.setAccessible(true);
                Map map = (Map) memberValues.get(handler);
                map.put("value","execution(public * com.zed.aopplugin.run.*.*(..))");
                System.out.println(pointcut.value());
                break;
            }
        }
    }

    @Pointcut("execution(public * com.zed.aopplugin.run2.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(1);
        this.beforeAspect();

    }

    @AfterReturning("pointcut()")
    public void afterRet() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(2);
        Method[] methods = MyAspect.class.getDeclaredMethods();
        for (Method method: methods) {
            Pointcut pointcut = method.getAnnotation(Pointcut.class);
            if (pointcut != null)
                System.out.println(pointcut.value());
        }
    }

}
