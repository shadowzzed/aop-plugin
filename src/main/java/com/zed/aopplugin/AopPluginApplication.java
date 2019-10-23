package com.zed.aopplugin;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AopPluginApplication {

    public static void main(String[] args)  {
        SpringApplication.run(AopPluginApplication.class, args);
    }


}



