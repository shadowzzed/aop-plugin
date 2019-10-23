package com.zed.aopplugin.aspect;

import com.zed.aopplugin.ConstentConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
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

    private static List<String> list = new ArrayList<>(10);

    static {
        try {
            FileInputStream inputStream = new FileInputStream(ConstentConfig.FILEPATH);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            if ((str = bufferedReader.readLine()) != null)
                list.add(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 检查类
     * @return
     */
    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            log.info("正在检查 {}, list大小: {}" ,clazz.getName(),list.size());
            for (String str: list) {
                log.info("当前正在匹配" + str);
                if (clazz.getName().contains(str)) {
                    System.out.println(str);
                    return true;
                }
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
