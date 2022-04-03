package de.metas.ui.web.view.json;
 import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.json.JSONDocumentFilterDescriptor;
import de.metas.ui.web.view.ViewCloseAction;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.descriptor.IncludedViewLayout;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutElement;
import de.metas.ui.web.window.datatypes.json.JSONDocumentLayoutOptions;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import lombok.Builder;
import lombok.Value;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewLayout {

@JsonProperty("viewId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String viewId;

@JsonProperty("type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
 private  WindowId type;

@JsonProperty("windowId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  WindowId windowId;

@JsonProperty("profileId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  ViewProfileId profileId;

@JsonProperty("caption")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String caption;

@JsonProperty("description")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String description;

@JsonProperty("emptyResultText")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String emptyResultText;

@JsonProperty("emptyResultHint")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String emptyResultHint;

@JsonProperty("elements")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentLayoutElement> elements;

@JsonProperty("filters")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentFilterDescriptor> filters;

 public  String PROPERTY_supportAttributes;

@JsonProperty(value = PROPERTY_supportAttributes)
 private  boolean supportAttributes;

@JsonProperty("supportTree")
 private  boolean supportTree;

@JsonProperty("collapsible")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean collapsible;

@JsonProperty("expandedDepth")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer expandedDepth;

@JsonProperty("allowedCloseActions")
 private  Set<ViewCloseAction> allowedCloseActions;

@JsonProperty("includedView")
 private  JSONIncludedViewSupport includedView;

@Deprecated
@JsonProperty("supportIncludedView")
 private  boolean supportIncludedView;

@Deprecated
@JsonProperty("supportIncludedViewOnSelect")
 private  Boolean supportIncludedViewOnSelect;

@JsonProperty("supportNewRecord")
 private  boolean supportNewRecord;

@JsonProperty("newRecordCaption")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String newRecordCaption;

@JsonProperty("supportOpenRecord")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean supportOpenRecord;

@JsonProperty("supportGeoLocations")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean supportGeoLocations;

@JsonProperty("focusOnFieldName")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String focusOnFieldName;

 private  boolean openOnSelect;

 private  boolean blurWhenOpen;


public boolean hasElements(){
    return !elements.isEmpty();
}


public String getEmptyResultText(){
    return emptyResultText;
}


public boolean isSupportAttributes(){
    return supportAttributes;
}


public String getCaption(){
    return caption;
}


public String getDescription(){
    return description;
}


public JSONIncludedViewSupport fromNullable(IncludedViewLayout includedViewLayout){
    if (includedViewLayout == null) {
        return null;
    }
    return builder().openOnSelect(includedViewLayout.isOpenOnSelect()).blurWhenOpen(includedViewLayout.isBlurWhenOpen()).build();
}


public void setSupportAttributes(boolean supportAttributes){
    this.supportAttributes = supportAttributes;
}


public void enableNewRecord(String newRecordCaption){
    supportNewRecord = true;
    this.newRecordCaption = newRecordCaption;
}


public JSONViewLayout of(ViewLayout gridLayout,JSONDocumentLayoutOptions options){
    return new JSONViewLayout(gridLayout, options);
}


public List<JSONDocumentLayoutElement> getElements(){
    return elements;
}


public List<JSONDocumentFilterDescriptor> getFilters(){
    return filters;
}


public String getEmptyResultHint(){
    return emptyResultHint;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("AD_Window_ID", windowId).add("caption", caption).add("elements", elements.isEmpty() ? null : elements).add("filters", filters.isEmpty() ? null : filters).toString();
}


public void setViewId(String viewId){
    this.viewId = viewId;
}


public boolean isSupportTree(){
    return supportTree;
}


}