package de.metas.ui.web.window.events;
 import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.websocket.WebSocketConfig;
import de.metas.ui.web.websocket.WebsocketEndpointAware;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONIncludedTabInfo;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.util.time.SystemTime;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@EqualsAndHashCode
@ToString
public class JSONDocumentChangedWebSocketEvent implements WebsocketEndpointAware{

@JsonProperty("windowId")
 private  WindowId windowId;

@JsonProperty("id")
 private  DocumentId id;

@JsonProperty("timestamp")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String timestamp;

@JsonProperty("stale")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean stale;

@JsonProperty("includedTabsInfo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Map<String,JSONIncludedTabInfo> includedTabsInfoByTabId;


public Map<String,JSONIncludedTabInfo> getIncludedTabsInfo(){
    if (includedTabsInfoByTabId == null) {
        includedTabsInfoByTabId = new HashMap<>();
    }
    return includedTabsInfoByTabId;
}


public void markRootDocumentAsStaled(){
    stale = Boolean.TRUE;
}


public void staleTabs(Collection<DetailId> tabIds){
    tabIds.stream().map(this::getIncludedTabInfo).forEach(JSONIncludedTabInfo::markAllRowsStaled);
}


public void mergeFrom(JSONDocumentChangedWebSocketEvent from){
    if (!Objects.equals(windowId, from.windowId) || !Objects.equals(id, from.id)) {
        throw new AdempiereException("Cannot merge events because they are not matching").setParameter("from", from).setParameter("to", this).appendParametersToMessage();
    }
    if (from.stale != null && from.stale) {
        stale = from.stale;
    }
    from.getIncludedTabsInfo().values().forEach(this::addIncludedTabInfo);
}


public void staleIncludedRows(DetailId tabId,DocumentIdsSelection rowIds){
    getIncludedTabInfo(tabId).staleRows(rowIds);
}


@Override
@JsonIgnore
public String getWebsocketEndpoint(){
    return WebSocketConfig.buildDocumentTopicName(windowId, id);
}


public JSONDocumentChangedWebSocketEvent rootDocument(WindowId windowId,DocumentId documentId){
    return new JSONDocumentChangedWebSocketEvent(windowId, documentId);
}


public JSONDocumentChangedWebSocketEvent copy(){
    return new JSONDocumentChangedWebSocketEvent(this);
}


public void addIncludedTabInfo(JSONIncludedTabInfo tabInfo){
    getIncludedTabsInfo().compute(tabInfo.getTabId().toJson(), (tabId, existingTabInfo) -> {
        if (existingTabInfo == null) {
            return tabInfo.copy();
        } else {
            existingTabInfo.mergeFrom(tabInfo);
            return existingTabInfo;
        }
    });
}


public JSONIncludedTabInfo getIncludedTabInfo(DetailId tabId){
    return getIncludedTabsInfo().computeIfAbsent(tabId.toJson(), k -> JSONIncludedTabInfo.newInstance(tabId));
}


}