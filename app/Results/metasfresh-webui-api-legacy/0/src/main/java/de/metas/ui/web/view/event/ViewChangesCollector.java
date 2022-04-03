package de.metas.ui.web.view.event;
 import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.trx.api.ITrxListenerManager.TrxEventTiming;
import org.adempiere.ad.trx.api.ITrxManager;
import org.adempiere.ad.trx.api.OnTrxMissingPolicy;
import org.adempiere.util.lang.IAutoCloseable;
import org.compiere.SpringContextHolder;
import org.compiere.util.Util;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.view.IView;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.websocket.WebsocketSender;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.util.Services;
import lombok.NonNull;
public class ViewChangesCollector implements IAutoCloseable{

 private  Logger logger;

 private  ThreadLocal<ViewChangesCollector> THREADLOCAL;

 private  String TRXPROPERTY_Name;

@Autowired
@Lazy
 private  WebsocketSender websocketSender;

 private  boolean autoflush;

 private  AtomicBoolean closed;

 private  Map<ViewId,ViewChanges> viewChangesMap;

 private  String createdStackTrace;

 private  String closedStackTrace;


public void assertNotClosed(){
    if (closed.get()) {
        throw new IllegalStateException("Collector " + this + " was already closed" + "\n\nCreated stacktrace: " + createdStackTrace + "\n\nClosed stacktrace: " + closedStackTrace);
    }
}


public void collectFullyChanged(IView view){
    viewChanges(view).setFullyChanged();
    autoflushIfEnabled();
}


public ViewChanges viewChanges(ViewId viewId){
    assertNotClosed();
    return viewChangesMap.computeIfAbsent(viewId, ViewChanges::new);
}


public IAutoCloseable currentOrNewThreadLocalCollector(){
    final ViewChangesCollector currentCollector = THREADLOCAL.get();
    if (currentCollector != null) {
        return currentCollector::flush;
    } else {
        final ViewChangesCollector newCollector = new ViewChangesCollector(false);
        THREADLOCAL.set(newCollector);
        return () -> {
            newCollector.close();
            THREADLOCAL.remove();
        };
    }
}


public List<ViewChanges> getAndClean(){
    if (viewChangesMap.isEmpty()) {
        return ImmutableList.of();
    }
    final List<ViewChanges> changesList = ImmutableList.copyOf(viewChangesMap.values());
    viewChangesMap.clear();
    return changesList;
}


public void collectFromChanges(ViewChanges changes){
    viewChanges(changes.getViewId()).collectFrom(changes);
    autoflushIfEnabled();
}


public void sendToWebsocket(JSONViewChanges jsonChangeEvent){
    final String endpoint = WebSocketConfig.buildViewNotificationsTopicName(jsonChangeEvent.getViewId());
    try {
        websocketSender.convertAndSend(endpoint, jsonChangeEvent);
        logger.debug("Send to websocket {}: {}", endpoint, jsonChangeEvent);
    } catch (final Exception ex) {
        logger.warn("Failed sending to websocket {}: {}", endpoint, jsonChangeEvent, ex);
    }
}


public ViewChangesCollector getCurrentOrAutoflush(){
    final ViewChangesCollector collector = getCurrentOrNull();
    if (collector != null) {
        return collector;
    }
    final boolean autoflush = true;
    return new ViewChangesCollector(autoflush);
}


public boolean isClosed(){
    return closed.get();
}


public void flush(){
    final List<ViewChanges> changesList = getAndClean();
    if (changesList.isEmpty()) {
        return;
    }
    // 
    // Try flushing to parent collector if any
    final ViewChangesCollector parentCollector = getParentOrNull();
    if (parentCollector != null) {
        logger.trace("Flushing {} to parent collector: {}", this, parentCollector);
        changesList.forEach(parentCollector::collectFromChanges);
    } else // 
    // Fallback: flush to websocket
    {
        logger.trace("Flushing {} to websocket", this);
        changesList.stream().filter(ViewChanges::hasChanges).map(JSONViewChanges::of).forEach(this::sendToWebsocket);
    }
}


public void collectRowsChanged(IView view,Collection<DocumentId> rowIds){
    viewChanges(view).addChangedRowIds(rowIds);
    autoflushIfEnabled();
}


public ViewChangesCollector getCurrentOrNull(){
    // 
    // Try getting thread level collector
    ViewChangesCollector threadLocalCollector = THREADLOCAL.get();
    if (threadLocalCollector != null) {
        if (threadLocalCollector.isClosed()) {
            // shall not happen
            THREADLOCAL.remove();
            threadLocalCollector = null;
        } else {
            return threadLocalCollector;
        }
    }
    // 
    // Try get/create transaction level collector
    final ITrxManager trxManager = Services.get(ITrxManager.class);
    final ITrx currentTrx = trxManager.getThreadInheritedTrx(OnTrxMissingPolicy.ReturnTrxNone);
    if (!trxManager.isNull(currentTrx)) {
        return currentTrx.getProperty(TRXPROPERTY_Name, trx -> {
            final ViewChangesCollector collector = new ViewChangesCollector();
            trx.getTrxListenerManager().newEventListener(TrxEventTiming.AFTER_COMMIT).registerHandlingMethod(innerTrx -> collector.close());
            return collector;
        });
    }
    // 
    // Fallback: return null
    return null;
}


@Override
public void close(){
    // Mark as closed. Stop here if already marked as closed.
    final boolean alreadyClosed = closed.getAndSet(true);
    if (alreadyClosed) {
        return;
    }
    closedStackTrace = Util.dumpStackTraceToString(new Exception());
    flush();
}


public void collectRowChanged(IView view,DocumentId rowId){
    viewChanges(view).addChangedRowId(rowId);
    autoflushIfEnabled();
}


public ViewChangesCollector getParentOrNull(){
    final ViewChangesCollector threadLocalCollector = getCurrentOrNull();
    if (threadLocalCollector != null && threadLocalCollector != this) {
        return threadLocalCollector;
    }
    return null;
}


public void autoflushIfEnabled(){
    if (!autoflush) {
        return;
    }
    flush();
}


}