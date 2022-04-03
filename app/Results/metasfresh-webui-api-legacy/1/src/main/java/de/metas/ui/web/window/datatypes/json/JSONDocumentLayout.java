package de.metas.ui.web.window.datatypes.json;
 import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.json.JSONDocumentFilterDescriptor;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentLayoutDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutDetailDescriptor;
import de.metas.ui.web.window.descriptor.DocumentLayoutSingleRow;
import io.swagger.annotations.ApiModel;
import lombok.NonNull;
@ApiModel("layout")
@SuppressWarnings("serial")
public class JSONDocumentLayout implements Serializable{

@JsonProperty("windowId")
 private  WindowId windowId;

@JsonProperty("type")
@Deprecated
 private  WindowId type;

@JsonProperty("tabId")
@JsonInclude(Include.NON_NULL)
 private  DetailId tabId;

@Deprecated
@JsonProperty("tabid")
@JsonInclude(Include.NON_NULL)
 private  DetailId tabid;

@JsonProperty("internalName")
@JsonInclude(Include.NON_EMPTY)
 private  String internalName;

@JsonProperty("caption")
@JsonInclude(Include.NON_EMPTY)
 private  String caption;

@JsonProperty("documentSummaryElement")
@JsonInclude(Include.NON_NULL)
 private  JSONDocumentLayoutElement documentSummaryElement;

@JsonProperty("docActionElement")
@JsonInclude(Include.NON_NULL)
 private  JSONDocumentLayoutElement docActionElement;

@JsonProperty("sections")
@JsonInclude(Include.NON_EMPTY)
 private  List<JSONDocumentLayoutSection> sections;

@JsonProperty("tabs")
@JsonInclude(Include.NON_EMPTY)
 private  List<JSONDocumentLayoutTab> tabs;

@JsonProperty("filters")
@JsonInclude(Include.NON_EMPTY)
 private  List<JSONDocumentFilterDescriptor> filters;

@JsonProperty("emptyResultText")
@JsonInclude(Include.NON_EMPTY)
 private  String emptyResultText;

@JsonProperty("emptyResultHint")
@JsonInclude(Include.NON_EMPTY)
 private  String emptyResultHint;

 private  Map<String,Object> otherProperties;


@JsonAnyGetter
public Map<String,Object> getOtherProperties(){
    return otherProperties;
}


public JSONDocumentLayout ofHeaderLayout(DocumentLayoutDescriptor layout,JSONDocumentLayoutOptions options){
    return new JSONDocumentLayout(layout, options);
}


public JSONDocumentLayout putDebugProperty(String name,Object jsonValue){
    otherProperties.put("debug-" + name, jsonValue);
    return this;
}


public void putDebugProperties(Map<String,?> debugProperties){
    if (debugProperties == null || debugProperties.isEmpty()) {
        return;
    }
    for (final Map.Entry<String, ?> e : debugProperties.entrySet()) {
        putDebugProperty(e.getKey(), e.getValue());
    }
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("windowId", windowId).add("sections", sections.isEmpty() ? null : sections).add("tabGroups", tabs.isEmpty() ? null : tabs).add("filters", (filters == null || filters.isEmpty()) ? null : filters).toString();
}


public JSONDocumentLayout ofDetailTab(DocumentLayoutDetailDescriptor detailLayout,JSONDocumentLayoutOptions jsonOpts){
    return new JSONDocumentLayout(detailLayout, jsonOpts);
}


@JsonAnySetter
public void putOtherProperty(String name,Object jsonValue){
    otherProperties.put(name, jsonValue);
}


}