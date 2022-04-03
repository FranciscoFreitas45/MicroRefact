package de.metas.ui.web.window.events;
 import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONIncludedTabInfo;
import de.metas.ui.web.window.descriptor.DetailId;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
@ToString
public class JSONDocumentChangedWebSocketEventCollector {

 private WindowId windowId;

 private DocumentId documentId;

 private  LinkedHashMap<EventKey,JSONDocumentChangedWebSocketEvent> events;


public void staleTabs(WindowId windowId,DocumentId documentId,Set<DetailId> tabIds){
    final JSONDocumentChangedWebSocketEvent event = getCreateEvent(windowId, documentId);
    event.staleTabs(tabIds);
}


public JSONDocumentChangedWebSocketEvent getCreateEvent(WindowId windowId,DocumentId documentId){
    final EventKey key = new EventKey(windowId, documentId);
    return events.computeIfAbsent(key, this::createEvent);
}


public void mergeFrom(EventKey key,JSONDocumentChangedWebSocketEvent from){
    events.compute(key, (k, existingEvent) -> {
        if (existingEvent == null) {
            return from.copy();
        } else {
            existingEvent.mergeFrom(from);
            return existingEvent;
        }
    });
}


public boolean isEmpty(){
    return events.isEmpty();
}


public JSONDocumentChangedWebSocketEvent createEvent(EventKey key){
    return JSONDocumentChangedWebSocketEvent.rootDocument(key.getWindowId(), key.getDocumentId());
}


public void staleRootDocument(WindowId windowId,DocumentId documentId){
    final JSONDocumentChangedWebSocketEvent event = getCreateEvent(windowId, documentId);
    event.markRootDocumentAsStaled();
}


public JSONDocumentChangedWebSocketEventCollector newInstance(){
    return new JSONDocumentChangedWebSocketEventCollector();
}


public List<JSONDocumentChangedWebSocketEvent> getEventsAndClear(){
    final List<JSONDocumentChangedWebSocketEvent> eventsList = ImmutableList.copyOf(events.values());
    events.clear();
    return eventsList;
}


public void staleTab(WindowId windowId,DocumentId documentId,DetailId tabId){
    staleTabs(windowId, documentId, ImmutableSet.of(tabId));
}


public void staleIncludedDocuments(WindowId windowId,DocumentId documentId,DetailId tabId,DocumentIdsSelection rowIds){
    final JSONDocumentChangedWebSocketEvent event = getCreateEvent(windowId, documentId);
    event.staleIncludedRows(tabId, rowIds);
}


}