package edu.xr.campusweibo.config.LoggingConfiguration;
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
public class LogbackLoggerContextListener extends ContextAwareBaseimplements LoggerContextListener{


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