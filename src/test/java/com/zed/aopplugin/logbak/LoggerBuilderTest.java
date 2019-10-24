package com.zed.aopplugin.logbak;

import ch.qos.logback.classic.Logger;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zeluo
 * @date 2019/10/24 13:53
 */
@SpringBootTest
@Slf4j
class LoggerBuilderTest {

    @Test
    public void test() {
        LoggerBuilder builder= new LoggerBuilder();
        Logger logger = builder.getLogger("test");
//        for (int i = 0; i < 1000000; i++) {
        logger.debug("debug");
            logger.warn("2222222222222222");
            logger.info("111111111");
            logger.error("1111");
//        }

    }


}