package config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
public class LogConfig {
    /**
     * Creating the anchor for the logging system(LoggerContext)
     *
     * PatternLayoutEncoder - set pattern for view
     * setLevel - highest level of logging
     * setAdditive - set to true if root should log too
     * @return logger
     */
    @Bean
    public Logger logger(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern("%date %level [%thread] %logger{10} [%file:%line] %msg%n");
        ple.setContext(loggerContext);
        ple.start();


        FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
        fileAppender.setFile("LogFile.log");
        fileAppender.setEncoder(ple);
        fileAppender.setContext(loggerContext);
        fileAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger("log");
        logger.addAppender(fileAppender);
        logger.setLevel(Level.DEBUG);
        logger.setAdditive(false);

        return logger;
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
}
