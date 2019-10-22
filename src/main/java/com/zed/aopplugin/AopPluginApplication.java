package com.zed.aopplugin;

import com.zed.aopplugin.aspect.MyAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class AopPluginApplication {

    @MyAnnotation(name = "111")
    private static String name;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ApplicationContext applicationContext = SpringApplication.run(AopPluginApplication.class, args);
        MyAspect bean = applicationContext.getBean(MyAspect.class);
        bean.beforeAspect();

    }

}
