package de.metas.ui.web.window.datatypes.json;
 import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.DocumentFilterDescriptor;
import de.metas.ui.web.document.filter.json.JSONDocumentFilterDescriptor;
import de.metas.ui.web.view.descriptor.ViewLayout;
import de.metas.ui.web.view.json.JSONViewOrderBy;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DetailId;
import de.metas.ui.web.window.descriptor.DocumentLayoutDetailDescriptor;
import de.metas.util.lang.CoalesceUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;
@ApiModel(value = "tab", description = "Window included tab layout")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDocumentLayoutTab {

@JsonProperty("windowId")
 private  WindowId windowId;

@JsonProperty("tabId")
 private  DetailId tabId;

@JsonProperty("internalName")
@JsonInclude(Include.NON_EMPTY)
 private  String internalName;

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

@// 
ApiModelProperty(allowEmptyValue = true, value = "Required to render the table columns for this tab.<br>" + "Therefore filled, unless <code>singleRowDetailLayout</code> is <code>true</code>.")
@JsonProperty("elements")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentLayoutElement> elements;

@// 
ApiModelProperty(allowEmptyValue = true, value = "Subtabs of this tab; note: there are either subtabs or sections, but not both.")
@JsonProperty("tabs")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentLayoutTab> subTabs;

@// 
ApiModelProperty(allowEmptyValue = true, value = "\"detail\" layout of the rows in this tab; required if singleRowDetailView is <code>true</code> and in advanced edit mode")
@JsonProperty("sections")
@JsonInclude(Include.NON_EMPTY)
 private  List<JSONDocumentLayoutSection> sections;

@JsonProperty("filters")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentFilterDescriptor> filters;

@JsonProperty("defaultOrderBys")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONViewOrderBy> defaultOrderBys;

@JsonProperty("supportQuickInput")
 private  boolean supportQuickInput;

@JsonProperty("queryOnActivate")
 private  boolean queryOnActivate;

@// 
ApiModelProperty(// 
allowEmptyValue = true, value = "If set to true, then the frontend shall render the tab in \"detail\" view. It can assume that there is at most one record to be shown in the tab.<br>" + "If empty, assume false.")
@JsonProperty("singleRowDetailView")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean singleRowDetailLayout;


public boolean isTabEmpty(JSONDocumentLayoutTab tab){
    final boolean singleRowDetailLayout = CoalesceUtil.coalesce(tab.singleRowDetailLayout, false);
    if (singleRowDetailLayout) {
        return tab.sections.isEmpty();
    }
    return tab.elements.isEmpty() && tab.subTabs.isEmpty();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("tabId", tabId).add("caption", caption).add("elements", elements.isEmpty() ? null : elements).add("filters", filters.isEmpty() ? null : filters).add("defaultOrderBys", defaultOrderBys.isEmpty() ? null : defaultOrderBys).toString();
}


public List<JSONDocumentLayoutTab> ofList(Collection<DocumentLayoutDetailDescriptor> details,JSONDocumentLayoutOptions jsonOpts){
    final Collection<DocumentFilterDescriptor> filters = null;
    // note: this used to be implemented with stream, but that was too cumbersome to debug
    final ImmutableList.Builder<JSONDocumentLayoutTab> result = ImmutableList.builder();
    for (final DocumentLayoutDetailDescriptor detail : details) {
        final JSONDocumentLayoutTab jsonTab = new JSONDocumentLayoutTab(detail, filters, jsonOpts);
        if (isTabEmpty(jsonTab)) {
            continue;
        }
        result.add(jsonTab);
    }
    return result.build();
}


}