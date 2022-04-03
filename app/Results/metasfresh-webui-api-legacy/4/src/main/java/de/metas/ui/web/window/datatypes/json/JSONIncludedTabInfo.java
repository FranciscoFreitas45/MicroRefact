package de.metas.ui.web.window.datatypes.json;
 import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.DetailId;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@ToString
@EqualsAndHashCode
public class JSONIncludedTabInfo {

@JsonProperty("tabId")
 private  String tabIdJson;

@JsonIgnore
 private  DetailId tabId;

@JsonProperty("stale")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean stale;

@JsonProperty("staleRowIds")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Set<DocumentId> staleRowIds;

@JsonProperty("allowCreateNew")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean allowCreateNew;

@JsonProperty("allowCreateNewReason")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String allowCreateNewReason;

@JsonProperty("allowDelete")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean allowDelete;

@JsonProperty("allowDeleteReason")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String allowDeleteReason;


public void setAllowDelete(boolean allowDelete,String reason){
    this.allowDelete = allowDelete;
    allowDeleteReason = reason;
}


public void setAllowCreateNew(boolean allowCreateNew,String reason){
    this.allowCreateNew = allowCreateNew;
    allowCreateNewReason = reason;
}


public void staleRows(DocumentIdsSelection rowIds){
    if (rowIds.isEmpty()) {
        // shall not happen
        return;
    } else if (rowIds.isAll()) {
        markAllRowsStaled();
    } else {
        staleRowIds.addAll(rowIds.toSet());
    }
}


public void mergeFrom(JSONIncludedTabInfo from){
    if (from.stale != null) {
        stale = from.stale;
    }
    staleRowIds.addAll(from.staleRowIds);
    if (from.allowCreateNew != null) {
        allowCreateNew = from.allowCreateNew;
        allowCreateNewReason = from.allowCreateNewReason;
    }
    if (from.allowDelete != null) {
        allowDelete = from.allowDelete;
        allowDeleteReason = from.allowDeleteReason;
    }
}


public DetailId getTabId(){
    return tabId;
}


public void markAllRowsStaled(){
    stale = Boolean.TRUE;
}


public JSONIncludedTabInfo newInstance(DetailId tabId){
    return new JSONIncludedTabInfo(tabId);
}


public JSONIncludedTabInfo copy(){
    return new JSONIncludedTabInfo(this);
}


}