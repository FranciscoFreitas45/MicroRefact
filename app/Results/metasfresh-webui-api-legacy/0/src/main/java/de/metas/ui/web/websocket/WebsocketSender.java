package de.metas.ui.web.websocket;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxListenerManager.TrxEventTiming;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.ad.trx.api.OnTrxMissingPolicy;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import de.metas.logging.LogManager;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class WebsocketSender implements InitializingBean{

 private  Logger logger;

 private  SimpMessagingTemplate websocketMessagingTemplate;

 private  WebsocketEventsLog eventsLog;

 private  WebsocketEventsQueue autoflushQueue;

@Value("${metasfresh.webui.websocket.logEventsEnabled:false}")
 private  boolean logEventsEnabledDefault;

 private  String destination;

 private  Object payload;

 private  boolean converted;

 private  String name;

 private  SimpMessagingTemplate websocketMessagingTemplate;

 private  WebsocketEventsLog eventsLog;

 private  boolean autoflush;

 private  List<WebsocketEvent> events;


public void setLogEventsMaxSize(int logEventsMaxSize){
    eventsLog.setLogEventsMaxSize(logEventsMaxSize);
}


public WebsocketEventsQueue createAndBindTrxQueue(ITrx trx){
    final String name = trx.getTrxName();
    final boolean autoflush = false;
    final WebsocketEventsQueue queue = new WebsocketEventsQueue(name, websocketMessagingTemplate, eventsLog, autoflush);
    // Bind
    trx.getTrxListenerManager().newEventListener(TrxEventTiming.AFTER_COMMIT).registerHandlingMethod(innerTrx -> queue.sendEventsAndClear());
    return queue;
}


public List<WebsocketEventLogRecord> getLoggedEvents(String destinationFilter){
    return eventsLog.getLoggedEvents(destinationFilter);
}


public void enqueueMessage(String destination,Message<?> message){
    final boolean converted = true;
    if (autoflush) {
        sendEvent(destination, message, converted);
    } else {
        enqueue(WebsocketEvent.builder().destination(destination).payload(message).converted(converted).build());
    }
}


public void sendMessage(String destination,Message<?> message){
    getQueue().enqueueMessage(destination, message);
}


public void enqueueObject(String destination,Object payload){
    final boolean converted = false;
    if (autoflush) {
        sendEvent(destination, payload, converted);
    } else {
        enqueue(WebsocketEvent.builder().destination(destination).payload(payload).converted(converted).build());
    }
}


public void sendEventsAndClear(){
    logger.debug("Sending all queued events");
    final List<WebsocketEvent> eventsToSend = new ArrayList<>(events);
    events.clear();
    eventsToSend.forEach(this::sendEvent);
}


public void setLogEventsEnabled(boolean enabled){
    eventsLog.setLogEventsEnabled(enabled);
}


public void convertAndSend(String destination,Object event){
    getQueue().enqueueObject(destination, event);
}


public void enqueue(WebsocketEvent event){
    events.add(event);
    logger.debug("[name={}] Enqueued event={}", name, event);
}


@Override
public void afterPropertiesSet(){
    eventsLog.setLogEventsEnabled(logEventsEnabledDefault);
}


public WebsocketEventsQueue getQueue(){
    final ITrxManager trxManager = Services.get(ITrxManager.class);
    final ITrx trx = trxManager.getThreadInheritedTrx(OnTrxMissingPolicy.ReturnTrxNone);
    if (!trxManager.isActive(trx)) {
        return autoflushQueue;
    } else if (!trx.getTrxListenerManager().canRegisterOnTiming(TrxEventTiming.AFTER_COMMIT)) {
        return autoflushQueue;
    } else {
        return trx.getProperty(WebsocketEventsQueue.class.getName(), () -> createAndBindTrxQueue(trx));
    }
}


public void sendEvent(String destination,Object payload,boolean converted){
    logger.debug("[name={}] Sending to destination={}: payload={}", name, destination, payload);
    if (converted) {
        final Message<?> message = (Message<?>) payload;
        websocketMessagingTemplate.send(destination, message);
    } else {
        websocketMessagingTemplate.convertAndSend(destination, payload);
        eventsLog.logEvent(destination, payload);
    }
}


}