package com.zed.aopplugin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Zeluo
 * @date 2019/10/17 17:14
 */
@Component
public class ConstentConfig {

//    @Value("${aop.config.filepath}")
    public static final String FILEPATH = "D:\\workspace-commons\\logs\\test.txt";

}
