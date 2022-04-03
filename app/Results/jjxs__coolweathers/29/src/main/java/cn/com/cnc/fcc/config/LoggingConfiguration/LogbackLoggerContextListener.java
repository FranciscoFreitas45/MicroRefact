package cn.com.cnc.fcc.config.LoggingConfiguration;
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