package de.metas.ui.web.websocket;
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
@Component
public class WebSocketProducersRegistry {

 private  Logger logger;

 private  ScheduledExecutorService scheduler;

@Autowired
 private  WebsocketSender websocketSender;

@Autowired
 private  ApplicationContext context;

 private  ConcurrentHashMap<String,WebSocketProducerFactory> _producerFactoriesByTopicNamePrefix;

 private  ConcurrentHashMap<String,WebSocketProducerInstance> _producersByTopicName;

 private  String topicName;

 private  WebSocketProducer producer;

 private  ScheduledExecutorService scheduler;

 private  WebsocketSender websocketSender;

 private  Set<String> subscribedSessionIds;

 private  ScheduledFuture<?> scheduledFuture;


public WebSocketProducerInstance getExistingWebSocketProducerInstanceOrNull(String topicName){
    return _producersByTopicName.get(topicName);
}


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


public void onSessionDisconnect(String sessionId){
    forEachExistingWebSocketProducerInstance(producer -> producer.unsubscribe(sessionId));
}


public WebSocketProducerFactory getWebSocketProducerFactoryOrNull(String topicName){
    if (topicName == null || topicName.isEmpty()) {
        return null;
    }
    return _producerFactoriesByTopicNamePrefix.entrySet().stream().filter(topicPrefixAndFactory -> topicName.startsWith(topicPrefixAndFactory.getKey())).map(topicPrefixAndFactory -> topicPrefixAndFactory.getValue()).findFirst().orElse(null);
}


public void registerProducerFactory(WebSocketProducerFactory producerFactory){
    Check.assumeNotNull(producerFactory, "Parameter producerFactory is not null");
    final String topicNamePrefix = producerFactory.getTopicNamePrefix();
    Check.assumeNotEmpty(topicNamePrefix, "topicNamePrefix is not empty");
    _producerFactoriesByTopicNamePrefix.compute(topicNamePrefix, (k, existingProducerFactory) -> {
        if (existingProducerFactory == null) {
            return producerFactory;
        } else {
            throw new IllegalArgumentException("Registering more then one producer factory for same topic prefix is not allowed" + "\n Topic prefix: " + topicNamePrefix + "\n Existing producer factory: " + existingProducerFactory + "\n New producer factory: " + producerFactory);
        }
    });
    logger.info("Registered producer factory: {}", producerFactory);
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


public WebSocketProducerInstance getCreateWebSocketProducerInstanceOrNull(String topicName){
    final WebSocketProducerInstance existingProducer = _producersByTopicName.get(topicName);
    if (existingProducer != null) {
        return existingProducer;
    }
    final WebSocketProducerFactory producerFactory = getWebSocketProducerFactoryOrNull(topicName);
    if (producerFactory == null) {
        return null;
    }
    return _producersByTopicName.computeIfAbsent(topicName, k -> {
        final WebSocketProducer producer = producerFactory.createProducer(topicName);
        return new WebSocketProducerInstance(topicName, producer, scheduler, websocketSender);
    });
}


public void onTopicSubscribed(String sessionId,String topicName){
    final WebSocketProducerInstance producer = getCreateWebSocketProducerInstanceOrNull(topicName);
    if (producer == null) {
        return;
    }
    producer.subscribe(sessionId);
}


@PostConstruct
public void registerProducerFactoriesFromContext(){
    BeanFactoryUtils.beansOfTypeIncludingAncestors(context, WebSocketProducerFactory.class).values().forEach(producerFactory -> registerProducerFactory(producerFactory));
}


public void forEachExistingWebSocketProducerInstance(Consumer<WebSocketProducerInstance> action){
    final long parallelismThreshold = Long.MAX_VALUE;
    _producersByTopicName.forEachValue(parallelismThreshold, action);
}


public void onTopicUnsubscribed(String sessionId,String topicName){
    if (topicName == null) {
        onSessionDisconnect(sessionId);
        return;
    }
    final WebSocketProducerInstance producer = getExistingWebSocketProducerInstanceOrNull(topicName);
    if (producer == null) {
        return;
    }
    producer.unsubscribe(sessionId);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("topicName", topicName).add("producer", producer).toString();
}


}