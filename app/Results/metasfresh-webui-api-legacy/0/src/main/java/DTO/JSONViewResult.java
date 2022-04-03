package DTO;
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
public class JSONViewResult {

 private  WindowId type;

 private  WindowId windowId;

 private  String viewId;

 private  ViewProfileId profileId;

 private  WindowId parentWindowId;

 private  String parentViewId;

 private  String description;

 private  JSONViewHeaderProperties headerProperties;

 private  Long size;

 private  List<JSONViewOrderBy> orderBy;

 private  List<JSONStickyDocumentFilter> staticFilters;

 private  List<JSONDocumentFilter> filters;

 private  List<? extends JSONViewRowBase> result;

 private  Map<String,JSONViewResultColumn> columnsByFieldName;

 private  Integer firstRow;

 private  Integer pageLength;

 private  Boolean queryLimitHit;

 private  Integer queryLimit;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public JSONViewResult of(ViewResult viewResult,List<? extends JSONViewRowBase> rows,JSONOptions jsonOpts){
    return new JSONViewResult(viewResult, rows, jsonOpts);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("viewResult",viewResult);
.queryParam("rows",rows);
.queryParam("jsonOpts",jsonOpts);
JSONViewResult aux = restTemplate.getForObject(builder.toUriString(),JSONViewResult.class);
return aux;
}


}