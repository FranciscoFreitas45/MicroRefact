package de.metas.ui.web.websocket.WebSocketProducersRegistry;
 import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import org.adempiere.util.concurrent.CustomizableThreadFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.google.common.base.MoreObjects;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import lombok.NonNull;
public class WebSocketProducerInstance {

 private  String topicName;

 private  WebSocketProducer producer;

 private  ScheduledExecutorService scheduler;

 private  WebsocketSender websocketSender;

 private  Set<String> subscribedSessionIds;

 private  ScheduledFuture<?> scheduledFuture;


public void subscribe(String sessionId){
    Check.assumeNotEmpty(sessionId, "sessionId is not empty");
    subscribedSessionIds.add(sessionId);
    if (subscribedSessionIds.isEmpty()) {
        // shall not happen
        return;
    }
    logger.trace("{}: session {} subscribed", this, sessionId);
    // 
    // Check if the producer was already scheduled
    if (scheduledFuture != null) {
        return;
    }
    // 
    // Schedule producer
    final long initialDelayMillis = 1000;
    final long periodMillis = 1000;
    scheduledFuture = scheduler.scheduleAtFixedRate(this::executeAndPublish, initialDelayMillis, periodMillis, TimeUnit.MILLISECONDS);
    logger.trace("{}: start producing using initialDelayMillis={}, periodMillis={}", this, initialDelayMillis, periodMillis);
}


public void unsubscribe(String sessionId){
    subscribedSessionIds.remove(sessionId);
    logger.trace("{}: session {} unsubscribed", this, sessionId);
    // 
    // Stop producer if there is nobody listening
    if (!subscribedSessionIds.isEmpty()) {
        return;
    }
    if (scheduledFuture == null) {
        return;
    }
    try {
        scheduledFuture.cancel(true);
    } catch (final Exception ex) {
        logger.warn("{}: Failed stopping scheduled future: {}. Ignored and considering it as stopped", this, scheduledFuture, ex);
    }
    scheduledFuture = null;
    logger.debug("{} stopped", this);
}


public void executeAndPublish(){
    try {
        final JSONOptions jsonOpts = JSONOptions.newInstance();
        final Object event = producer.produceEvent(jsonOpts);
        websocketSender.convertAndSend(topicName, event);
        logger.trace("Event sent to {}: {}", topicName, event);
    } catch (final Exception ex) {
        logger.warn("Failed producing event for {}. Ignored.", this, ex);
    }
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("topicName", topicName).add("producer", producer).toString();
}


}