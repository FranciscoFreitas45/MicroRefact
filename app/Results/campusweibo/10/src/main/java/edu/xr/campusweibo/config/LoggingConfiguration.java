package edu.xr.campusweibo.config;
 import io.github.jhipster.config.JHipsterProperties;
import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import net.logstash.logback.appender.LogstashSocketAppender;
import net.logstash.logback.stacktrace.ShortenedThrowableConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class LoggingConfiguration {

 private  Logger log;

 private  LoggerContext context;

@Value("${spring.application.name}")
 private  String appName;

@Value("${server.port}")
 private  String serverPort;

 private  JHipsterProperties jHipsterProperties;


public void addLogstashAppender(LoggerContext context){
    log.info("Initializing Logstash logging");
    LogstashSocketAppender logstashAppender = new LogstashSocketAppender();
    logstashAppender.setName("LOGSTASH");
    logstashAppender.setContext(context);
    String customFields = "{\"app_name\":\"" + appName + "\",\"app_port\":\"" + serverPort + "\"}";
    // Set the Logstash appender config from JHipster properties
    logstashAppender.setSyslogHost(jHipsterProperties.getLogging().getLogstash().getHost());
    logstashAppender.setPort(jHipsterProperties.getLogging().getLogstash().getPort());
    logstashAppender.setCustomFields(customFields);
    // Limit the maximum length of the forwarded stacktrace so that it won't exceed the 8KB UDP limit of logstash
    ShortenedThrowableConverter throwableConverter = new ShortenedThrowableConverter();
    throwableConverter.setMaxLength(7500);
    throwableConverter.setRootCauseFirst(true);
    logstashAppender.setThrowableConverter(throwableConverter);
    logstashAppender.start();
    // Wrap the appender in an Async appender for performance
    AsyncAppender asyncLogstashAppender = new AsyncAppender();
    asyncLogstashAppender.setContext(context);
    asyncLogstashAppender.setName("ASYNC_LOGSTASH");
    asyncLogstashAppender.setQueueSize(jHipsterProperties.getLogging().getLogstash().getQueueSize());
    asyncLogstashAppender.addAppender(logstashAppender);
    asyncLogstashAppender.start();
    context.getLogger("ROOT").addAppender(asyncLogstashAppender);
}


@Override
public void onStart(LoggerContext context){
    addLogstashAppender(context);
}


@Override
public void onLevelChange(ch.qos.logback.classic.Logger logger,Level level){
// Nothing to do.
}


@Override
public void onReset(LoggerContext context){
    addLogstashAppender(context);
}


@Override
public boolean isResetResistant(){
    return true;
}


@Override
public void onStop(LoggerContext context){
// Nothing to do.
}


}