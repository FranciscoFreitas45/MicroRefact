package de.metas.ui.web.view.json;
 import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.ui.web.document.filter.json.JSONDocumentFilter;
import de.metas.ui.web.document.filter.json.JSONStickyDocumentFilter;
import de.metas.ui.web.view.IViewRow;
import de.metas.ui.web.view.IViewRowOverrides;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.view.ViewProfileId;
import de.metas.ui.web.view.ViewResult;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.GuavaCollectors;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONViewResult {

@JsonProperty("type")
@Deprecated
 private  WindowId type;

@JsonProperty("windowId")
 private  WindowId windowId;

@JsonProperty("viewId")
 private  String viewId;

@JsonProperty("profileId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  ViewProfileId profileId;

@JsonProperty("parentWindowId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  WindowId parentWindowId;

@JsonProperty("parentViewId")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  String parentViewId;

@JsonProperty("description")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String description;

@JsonProperty("headerProperties")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  JSONViewHeaderProperties headerProperties;

@JsonProperty("size")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Long size;

@JsonProperty("orderBy")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONViewOrderBy> orderBy;

@JsonProperty("staticFilters")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONStickyDocumentFilter> staticFilters;

@JsonProperty("filters")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  List<JSONDocumentFilter> filters;

@JsonProperty("result")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  List<? extends JSONViewRowBase> result;

@JsonProperty("columnsByFieldName")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Map<String,JSONViewResultColumn> columnsByFieldName;

@JsonProperty("firstRow")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer firstRow;

@JsonProperty("pageLength")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer pageLength;

@JsonProperty("queryLimitHit")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean queryLimitHit;

@JsonProperty("queryLimit")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Integer queryLimit;


public JSONViewResult of(ViewResult viewResult,List<? extends JSONViewRowBase> rows,JSONOptions jsonOpts){
    return new JSONViewResult(viewResult, rows, jsonOpts);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("viewId", viewId).add("AD_Window_ID", windowId).add("size", size).add("firstRow", firstRow).add("pageLength", pageLength).add("result", result).toString();
}


}