package com.zed.aopplugin.run;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测试类
 * @author Zeluo
 * @date 2019/10/22 11:35
 */
//@Component
public class Run1 {

    @Scheduled(fixedDelay = 3000)
    public void run() {
        System.out.println("RUN1");
    }
}
