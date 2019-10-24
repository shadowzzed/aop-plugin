package com.zed.aopplugin.logbak;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.rolling.RollingFileAppender;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zeluo
 * @date 2019/10/24 10:13
 */
@Component
public class LoggerBuilder {

    private static final Map<String, Logger> container = new HashMap<>();

    public Logger getLogger(String name) {
        Logger logger = container.get(name);
        if (logger != null)
            return logger;
        synchronized (LoggerBuilder.class) {
            logger = container.get(name);
            if (logger != null)
                return logger;
            logger = build(name);
            container.put(name, logger);
        }
        return logger;
    }

    private Logger build(String name) {
        RollingFileAppender errorAppender = new LoggerHelper().getAppender(name, Level.ERROR);
        RollingFileAppender infoAppender = new LoggerHelper().getAppender(name ,Level.INFO);
        RollingFileAppender warnAppender = new LoggerHelper().getAppender(name , Level.WARN);
        RollingFileAppender debugAppender = new LoggerHelper().getAppender(name ,Level.DEBUG);
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger("File");
        logger.setAdditive(true);
        logger.addAppender(infoAppender);
        logger.addAppender(debugAppender);
        logger.addAppender(warnAppender);
        logger.addAppender(errorAppender);
        return logger;
    }
}
