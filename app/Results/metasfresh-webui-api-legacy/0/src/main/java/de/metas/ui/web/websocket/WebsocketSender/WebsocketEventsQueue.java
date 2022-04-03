package de.metas.ui.web.websocket.WebsocketSender;
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
public class WebsocketEventsQueue {

 private  String name;

 private  SimpMessagingTemplate websocketMessagingTemplate;

 private  WebsocketEventsLog eventsLog;

 private  boolean autoflush;

 private  List<WebsocketEvent> events;


public void enqueue(WebsocketEvent event){
    events.add(event);
    logger.debug("[name={}] Enqueued event={}", name, event);
}


public void enqueueMessage(String destination,Message<?> message){
    final boolean converted = true;
    if (autoflush) {
        sendEvent(destination, message, converted);
    } else {
        enqueue(WebsocketEvent.builder().destination(destination).payload(message).converted(converted).build());
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


}