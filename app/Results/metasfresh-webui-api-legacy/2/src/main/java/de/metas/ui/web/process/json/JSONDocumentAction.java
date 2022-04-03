package de.metas.ui.web.process.json;
 import java.io.Serializable;
import java.time.Duration;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import org.compiere.util.TimeUtil;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import de.metas.process.RelatedProcessDescriptor.DisplayPlace;
import de.metas.ui.web.process.descriptor.WebuiRelatedProcessDescriptor;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import lombok.NonNull;
@SuppressWarnings("serial")
public class JSONDocumentAction implements Serializable{

 public  Comparator<JSONDocumentAction> ORDERBY_QuickActionFirst_Caption;

@JsonProperty("processId")
 private  String processId;

@JsonProperty("caption")
 private  String caption;

@JsonProperty("description")
 private  String description;

@JsonProperty("displayPlaces")
 private  Set<DisplayPlace> displayPlaces;

@JsonProperty("quickAction")
@Deprecated
 private  boolean quickAction;

@JsonProperty("defaultQuickAction")
 private  boolean defaultQuickAction;

@JsonProperty("shortcut")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String shortcut;

@JsonProperty("disabled")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean disabled;

@JsonProperty("disabledReason")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String disabledReason;

@JsonProperty("disabledWithInternalReason")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean disabledWithInternalReason;

@JsonProperty("evaluateDuration")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String evaluateDurationStr;

@JsonProperty("internalName")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String internalName;

@JsonIgnore
 private  int sortNo;

 private  Map<String,Object> debugProperties;


public int getSortNo(){
    return sortNo;
}


public boolean isQuickAction(){
    return quickAction;
}


@JsonAnyGetter
public Map<String,Object> getDebugProperties(){
    return debugProperties;
}


@JsonIgnore
public boolean isEnabled(){
    return !isDisabled();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("caption", caption).add("processId", processId).add("quickAction", quickAction).add("defaultQuickAction", defaultQuickAction).toString();
}


public boolean isDefaultQuickAction(){
    return defaultQuickAction;
}


@JsonIgnore
public boolean isDisabled(){
    return disabled != null && disabled;
}


public String getCaption(){
    return caption;
}


public String getDescription(){
    return description;
}


}