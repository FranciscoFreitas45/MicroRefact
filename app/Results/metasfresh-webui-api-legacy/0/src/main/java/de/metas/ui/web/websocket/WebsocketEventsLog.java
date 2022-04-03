package de.metas.ui.web.websocket;
 import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
public class WebsocketEventsLog {

 private  Logger logger;

 private  AtomicBoolean logEventsEnabled;

 private  AtomicInteger logEventsMaxSize;

 private  List<WebsocketEventLogRecord> loggedEvents;


public void setLogEventsMaxSize(int logEventsMaxSizeNew){
    Preconditions.checkArgument(logEventsMaxSizeNew > 0, "logEventsMaxSize > 0");
    final int logEventsMaxSizeOld = logEventsMaxSize.getAndSet(logEventsMaxSizeNew);
    logger.info("Changed logEventsMaxSize from {} to {}", logEventsMaxSizeOld, logEventsMaxSizeNew);
}


public List<WebsocketEventLogRecord> getLoggedEvents(String destinationFilter){
    return getLoggedEvents().stream().filter(websocketEvent -> websocketEvent.isDestinationMatching(destinationFilter)).collect(ImmutableList.toImmutableList());
}


public void setLogEventsEnabled(boolean enabled){
    final boolean enabledOld = logEventsEnabled.getAndSet(enabled);
    logger.info("Changed logEventsEnabled from {} to {}", enabledOld, enabled);
}


public void logEvent(String destination,Object event){
    if (!logEventsEnabled.get()) {
        return;
    }
    synchronized (loggedEvents) {
        logger.info("{}: {}", destination, event);
        loggedEvents.add(new WebsocketEventLogRecord(destination, event));
        final int maxSize = logEventsMaxSize.get();
        while (loggedEvents.size() > maxSize) {
            loggedEvents.remove(0);
        }
    }
}


}