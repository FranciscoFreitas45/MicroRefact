package de.metas.ui.web.window.events;
 import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.ad.trx.api.OnTrxMissingPolicy;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.lang.IAutoCloseable;
import org.springframework.stereotype.Component;
import de.metas.ui.web.websocket.WebsocketSender;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONDocument;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.util.Services;
import lombok.NonNull;
@Component
public class DocumentWebsocketPublisher {

 private  ThreadLocal<JSONDocumentChangedWebSocketEventCollector> THREAD_LOCAL_COLLECTOR;

 private  WebsocketSender websocketSender;


public void sendAllAndClear(JSONDocumentChangedWebSocketEventCollector collector,WebsocketSender websocketSender){
    final List<JSONDocumentChangedWebSocketEvent> events = collector.getEventsAndClear();
    websocketSender.convertAndSend(events);
}


public void staleTabs(WindowId windowId,DocumentId documentId,Set<DetailId> tabIds){
    forCollector(collector -> collector.staleTabs(windowId, documentId, tabIds));
}


public IAutoCloseable temporaryCollectOnThisThread(){
    if (THREAD_LOCAL_COLLECTOR.get() != null) {
        throw new AdempiereException("A thread level collector was already set");
    }
    final JSONDocumentChangedWebSocketEventCollector collector = JSONDocumentChangedWebSocketEventCollector.newInstance();
    THREAD_LOCAL_COLLECTOR.set(collector);
    return new IAutoCloseable() {

        @Override
        public String toString() {
            return "AutoCloseable[" + collector + "]";
        }

        @Override
        public void close() {
            THREAD_LOCAL_COLLECTOR.set(null);
            sendAllAndClear(collector, websocketSender);
        }
    };
}


public void collectFrom(JSONDocumentChangedWebSocketEventCollector collector,JSONDocument event){
    final WindowId windowId = event.getWindowId();
    if (windowId == null) {
        return;
    }
    // Included document => nothing to publish about it
    if (event.getTabId() != null) {
        return;
    }
    final DocumentId documentId = event.getId();
    event.getIncludedTabsInfos().forEach(tabInfo -> collector.mergeFrom(windowId, documentId, tabInfo));
}


public void staleRootDocument(WindowId windowId,DocumentId documentId){
    forCollector(collector -> collector.staleRootDocument(windowId, documentId));
}


@Override
public String toString(){
    return "AutoCloseable[" + collector + "]";
}


@Override
public void close(){
    THREAD_LOCAL_COLLECTOR.set(null);
    sendAllAndClear(collector, websocketSender);
}


public void staleIncludedDocuments(WindowId windowId,DocumentId documentId,DetailId tabId,DocumentIdsSelection rowIds){
    forCollector(collector -> collector.staleIncludedDocuments(windowId, documentId, tabId, rowIds));
}


public void convertAndPublish(List<JSONDocument> jsonDocumentEvents){
    if (jsonDocumentEvents == null || jsonDocumentEvents.isEmpty()) {
        return;
    }
    final JSONDocumentChangedWebSocketEventCollector collectorToMerge = JSONDocumentChangedWebSocketEventCollector.newInstance();
    for (final JSONDocument jsonDocumentEvent : jsonDocumentEvents) {
        collectFrom(collectorToMerge, jsonDocumentEvent);
    }
    if (collectorToMerge.isEmpty()) {
        return;
    }
    forCollector(collector -> collector.mergeFrom(collectorToMerge));
}


public void forCollector(Consumer<JSONDocumentChangedWebSocketEventCollector> consumer){
    // 
    // Get the collector to use
    final JSONDocumentChangedWebSocketEventCollector collector;
    final boolean autoflush;
    final JSONDocumentChangedWebSocketEventCollector threadLocalCollector = THREAD_LOCAL_COLLECTOR.get();
    if (threadLocalCollector != null) {
        collector = threadLocalCollector;
        autoflush = false;
    } else {
        final ITrxManager trxManager = Services.get(ITrxManager.class);
        final ITrx trx = trxManager.getThreadInheritedTrx(OnTrxMissingPolicy.ReturnTrxNone);
        if (trxManager.isActive(trx)) {
            collector = trx.getPropertyAndProcessAfterCommit(JSONDocumentChangedWebSocketEventCollector.class.getName(), () -> JSONDocumentChangedWebSocketEventCollector.newInstance(), c -> sendAllAndClear(c, websocketSender));
            autoflush = false;
        } else {
            collector = JSONDocumentChangedWebSocketEventCollector.newInstance();
            autoflush = true;
        }
    }
    // 
    // Call the consumer
    consumer.accept(collector);
    // 
    // Autoflush if needed
    if (autoflush) {
        sendAllAndClear(collector, websocketSender);
    }
}


}