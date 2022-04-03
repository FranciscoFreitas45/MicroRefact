package cn.com.cnc.fcc.config;
 import java.net.InetSocketAddress;
import java.util.Iterator;
import io.github.jhipster.config.JHipsterProperties;
import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.boolex.OnMarkerEvaluator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.filter.EvaluatorFilter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterReply;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import net.logstash.logback.stacktrace.ShortenedThrowableConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class LoggingConfiguration {

 private  String LOGSTASH_APPENDER_NAME;

 private  String ASYNC_LOGSTASH_APPENDER_NAME;

 private  Logger log;

 private  LoggerContext context;

 private  String appName;

 private  String serverPort;

 private  JHipsterProperties jHipsterProperties;

public LoggingConfiguration(@Value("${spring.application.name}") String appName, @Value("${server.port}") String serverPort, JHipsterProperties jHipsterProperties) {
    this.appName = appName;
    this.serverPort = serverPort;
    this.jHipsterProperties = jHipsterProperties;
    if (jHipsterProperties.getLogging().getLogstash().isEnabled()) {
        addLogstashAppender(context);
        addContextListener(context);
    }
    if (jHipsterProperties.getMetrics().getLogs().isEnabled()) {
        setMetricsMarkerLogbackFilter(context);
    }
}
public void addLogstashAppender(LoggerContext context){
    log.info("Initializing Logstash logging");
    LogstashTcpSocketAppender logstashAppender = new LogstashTcpSocketAppender();
    logstashAppender.setName(LOGSTASH_APPENDER_NAME);
    logstashAppender.setContext(context);
    String customFields = "{\"app_name\":\"" + appName + "\",\"app_port\":\"" + serverPort + "\"}";
    // More documentation is available at: https://github.com/logstash/logstash-logback-encoder
    LogstashEncoder logstashEncoder = new LogstashEncoder();
    // Set the Logstash appender config from JHipster properties
    logstashEncoder.setCustomFields(customFields);
    // Set the Logstash appender config from JHipster properties
    logstashAppender.addDestinations(new InetSocketAddress(jHipsterProperties.getLogging().getLogstash().getHost(), jHipsterProperties.getLogging().getLogstash().getPort()));
    ShortenedThrowableConverter throwableConverter = new ShortenedThrowableConverter();
    throwableConverter.setRootCauseFirst(true);
    logstashEncoder.setThrowableConverter(throwableConverter);
    logstashEncoder.setCustomFields(customFields);
    logstashAppender.setEncoder(logstashEncoder);
    logstashAppender.start();
    // Wrap the appender in an Async appender for performance
    AsyncAppender asyncLogstashAppender = new AsyncAppender();
    asyncLogstashAppender.setContext(context);
    asyncLogstashAppender.setName(ASYNC_LOGSTASH_APPENDER_NAME);
    asyncLogstashAppender.setQueueSize(jHipsterProperties.getLogging().getLogstash().getQueueSize());
    asyncLogstashAppender.addAppender(logstashAppender);
    asyncLogstashAppender.start();
    context.getLogger("ROOT").addAppender(asyncLogstashAppender);
}


public void addContextListener(LoggerContext context){
    LogbackLoggerContextListener loggerContextListener = new LogbackLoggerContextListener();
    loggerContextListener.setContext(context);
    context.addListener(loggerContextListener);
}


@Override
public void onStart(LoggerContext context){
    addLogstashAppender(context);
}


@Override
public void onLevelChange(ch.qos.logback.classic.Logger logger,Level level){
// Nothing to do.
}


public void setMetricsMarkerLogbackFilter(LoggerContext context){
    log.info("Filtering metrics logs from all appenders except the {} appender", LOGSTASH_APPENDER_NAME);
    OnMarkerEvaluator onMarkerMetricsEvaluator = new OnMarkerEvaluator();
    onMarkerMetricsEvaluator.setContext(context);
    onMarkerMetricsEvaluator.addMarker("metrics");
    onMarkerMetricsEvaluator.start();
    EvaluatorFilter<ILoggingEvent> metricsFilter = new EvaluatorFilter<>();
    metricsFilter.setContext(context);
    metricsFilter.setEvaluator(onMarkerMetricsEvaluator);
    metricsFilter.setOnMatch(FilterReply.DENY);
    metricsFilter.start();
    for (ch.qos.logback.classic.Logger logger : context.getLoggerList()) {
        for (Iterator<Appender<ILoggingEvent>> it = logger.iteratorForAppenders(); it.hasNext(); ) {
            Appender<ILoggingEvent> appender = it.next();
            if (!appender.getName().equals(ASYNC_LOGSTASH_APPENDER_NAME)) {
                log.debug("Filter metrics logs from the {} appender", appender.getName());
                appender.setContext(context);
                appender.addFilter(metricsFilter);
                appender.start();
            }
        }
    }
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