package com.zed.aopplugin.logbak;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Zeluo
 * @date 2019/10/24 10:16
 */
@Component
public class LoggerHelper {

    public RollingFileAppender getAppender(String name, Level level) {
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        //滚动日志追加器
        RollingFileAppender appender = new RollingFileAppender();
        //控制台日志追加器
        ConsoleAppender consoleAppender = new ConsoleAppender();

        //过滤日志级别
        LevelFilter levelFilter = new LevelFilter();
        levelFilter.setLevel(level);
        levelFilter.setOnMatch(FilterReply.ACCEPT);
        levelFilter.setOnMismatch(FilterReply.DENY);
        levelFilter.start();
        appender.addFilter(levelFilter);

        String pattern = null;
        pattern = OptionHelper.substVars("D:/logs/"+ name+"/" + format.format(new Date())+"/"+ "111" +level.levelStr + ".log",context);
        //设置appender属性
        appender.setContext(context);
        appender.setName("FILE - " + name); //name 属性
        appender.setFile(pattern);
        appender.setAppend(true);
        appender.setPrudent(false);

        //设置rollingPolicy
        SizeAndTimeBasedRollingPolicy rollingPolicy = new SizeAndTimeBasedRollingPolicy();
        rollingPolicy.setContext(context);
        rollingPolicy.setMaxFileSize(FileSize.valueOf("1MB"));
        rollingPolicy.setFileNamePattern(OptionHelper.substVars("D:/logs/"+ name+"/" + format.format(new Date())+"/"+ level.levelStr + "/.%d{yyyy-MM-dd}.%i.log.zip",context));
        rollingPolicy.setMaxHistory(180);
        rollingPolicy.setTotalSizeCap(FileSize.valueOf("3GB"));
        rollingPolicy.setParent(appender);
        rollingPolicy.start();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d %p (%file:%line\\)- %m%n");
        encoder.start();

        appender.setRollingPolicy(rollingPolicy);
        appender.setEncoder(encoder);
        appender.start();
        return appender;
    }
}
