package de.metas.ui.web.view.event;
 import java.io.Serializable;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.datatypes.WindowId;
@SuppressWarnings("serial")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewChanges implements Serializable{

@JsonProperty("viewId")
 private  String viewId;

@JsonProperty("windowId")
 private  WindowId windowId;

@JsonProperty("fullyChanged")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean fullyChanged;

@JsonProperty("changedIds")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Set<String> changedIds;


public JSONViewChanges of(ViewChanges changes){
    return new JSONViewChanges(changes);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("viewId", viewId).add("windowId", windowId).add("fullyChanged", fullyChanged).add("changedIds", changedIds).toString();
}


public String getViewId(){
    return viewId;
}


public Boolean getFullyChanged(){
    return fullyChanged;
}


public WindowId getWindowId(){
    return windowId;
}


}