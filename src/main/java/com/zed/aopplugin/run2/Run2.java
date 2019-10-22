package com.zed.aopplugin.run2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Zeluo
 * @date 2019/10/22 11:40
 */
@Component
public class Run2 {

    @Scheduled(fixedDelay = 5000)
    public void run() {
        System.out.println("RUN2");
    }
}
